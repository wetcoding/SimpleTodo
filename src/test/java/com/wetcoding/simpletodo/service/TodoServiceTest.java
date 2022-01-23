package com.wetcoding.simpletodo.service;

import com.wetcoding.simpletodo.dto.TodoItemDto;
import com.wetcoding.simpletodo.mapper.TodoItemMapper;
import com.wetcoding.simpletodo.model.TodoItem;
import com.wetcoding.simpletodo.model.enums.TodoItemStatus;
import com.wetcoding.simpletodo.repository.TodoItemRepository;
import com.wetcoding.simpletodo.service.bean.TodoServiceBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoItemRepository todoItemRepository;
    @Mock
    private TodoItemMapper todoItemMapper;

    @InjectMocks
    private TodoServiceBean todoService;

    @Test
    void getAllItems() {
        TodoItem todoItemEntity = new TodoItem();
        todoItemEntity.setId(1L);
        todoItemEntity.setBody("Test todo");
        todoItemEntity.setStatus(TodoItemStatus.CREATED);

        List<TodoItem> dbResult = List.of(todoItemEntity);

        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setBody("Test todo");
        todoItemDto.setId(1L);
        todoItemDto.setStatus(TodoItemStatus.CREATED);

        List<TodoItemDto> dtoResult = List.of(todoItemDto);

        when(todoItemRepository.findAll()).thenReturn(dbResult);
        when(todoItemMapper.toDtoList(dbResult)).thenReturn(List.of(todoItemDto));

        assertEquals(List.of(todoItemDto), todoService.getAllItems());
    }

    @Test
    void getItem_entityExist_thenOk() {
        TodoItem todoItemEntity = new TodoItem();
        todoItemEntity.setId(1L);
        todoItemEntity.setBody("Test todo");
        todoItemEntity.setStatus(TodoItemStatus.CREATED);

        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setBody("Test todo");
        todoItemDto.setId(1L);
        todoItemDto.setStatus(TodoItemStatus.CREATED);

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItemEntity));
        when(todoItemMapper.toDto(todoItemEntity)).thenReturn(todoItemDto);

        assertEquals(todoItemDto, todoService.getItem(1L));
    }

    @Test
    void getItem_entityNotExist_thenThrow() {
        TodoItem todoItemEntity = new TodoItem();
        todoItemEntity.setId(1L);
        todoItemEntity.setBody("Test todo");
        todoItemEntity.setStatus(TodoItemStatus.CREATED);

        when(todoItemRepository.findById(1L)).thenThrow(new NoSuchElementException("No value present"));

        assertThrows(NoSuchElementException.class, () -> todoService.getItem(1L));
    }

    @Test
    void updateItem_entityExist_thenOk() {
        TodoItem todoItemEntity = new TodoItem();
        todoItemEntity.setId(1L);
        todoItemEntity.setBody("Test todo");
        todoItemEntity.setStatus(TodoItemStatus.CREATED);

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItemEntity));
        todoService.updateItem(1L, "New text");

        Mockito.verify(todoItemRepository).save(todoItemEntity);
        assertEquals("New text", todoItemEntity.getBody());
    }

    @Test
    void updateItem_entityNotExist_thenThrow() {
        when(todoItemRepository.findById(1L)).thenThrow(new NoSuchElementException("No value present"));

        assertThrows(NoSuchElementException.class, () -> todoService.updateItem(1L, "Some text"));
    }

    @Test
    void deleteItem_ifExist_thenOk() {
        TodoItem todoItemEntity = new TodoItem();
        todoItemEntity.setId(1L);
        todoItemEntity.setBody("Test todo");
        todoItemEntity.setStatus(TodoItemStatus.CREATED);


        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItemEntity));
        todoService.deleteItem(1L);

        Mockito.verify(todoItemRepository).delete(todoItemEntity);
    }

    @Test
    void createTodoItem() {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setBody("Test todo");
        todoItemDto.setId(1L);

        TodoItem todoItemEntity = new TodoItem();
        todoItemEntity.setId(1L);
        todoItemEntity.setBody("Test todo");
        todoItemEntity.setStatus(TodoItemStatus.CREATED);

        when(todoItemMapper.fromDto(todoItemDto)).thenReturn(todoItemEntity);

        todoService.createTodoItem(todoItemDto);

        Mockito.verify(todoItemRepository).save(todoItemEntity);
    }
}