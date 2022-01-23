package com.wetcoding.simpletodo.integration.service;

import com.wetcoding.simpletodo.dto.TodoItemDto;
import com.wetcoding.simpletodo.integration.AbstractIntegrationTest;
import com.wetcoding.simpletodo.model.TodoItem;
import com.wetcoding.simpletodo.model.enums.TodoItemStatus;
import com.wetcoding.simpletodo.repository.TodoItemRepository;
import com.wetcoding.simpletodo.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
class TodoServiceTest extends AbstractIntegrationTest {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired
    private TodoService todoService;


    @Test
    public void getAllItems() {
        TodoItem todoItem = new TodoItem();
        todoItem.setBody("Test");
        todoItem.setStatus(TodoItemStatus.CREATED);
        todoItemRepository.save(todoItem);

        List<TodoItemDto> allItems = todoService.getAllItems();

        TodoItemDto todoItemDto = allItems.stream().findFirst().orElseThrow();

        Assertions.assertEquals(1, allItems.size());
        Assertions.assertEquals("Test", todoItemDto.getBody());
        Assertions.assertEquals(TodoItemStatus.CREATED, todoItemDto.getStatus());
    }

    @Test
    public void getById() {
        TodoItem todoItem = new TodoItem();
        todoItem.setBody("Test");
        todoItem.setStatus(TodoItemStatus.CREATED);
        todoItemRepository.save(todoItem);

        Long expectedId = todoItemRepository.findAll().stream().findFirst().orElseThrow().getId();

        TodoItemDto item = todoService.getItem(expectedId);

        Assertions.assertEquals("Test", item.getBody());
        Assertions.assertEquals(TodoItemStatus.CREATED, item.getStatus());
    }

    @Test
    public void updateItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setBody("Test");
        todoItem.setStatus(TodoItemStatus.CREATED);
        todoItemRepository.save(todoItem);

        Long expectedId = todoItemRepository.findAll().stream().findFirst().orElseThrow().getId();

        todoService.updateItem(expectedId, "New text");

        TodoItem todoItem1 = todoItemRepository.findById(expectedId).orElseThrow();

        Assertions.assertEquals("New text", todoItem1.getBody());
    }

    @Test
    public void createItem() {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setBody("Test");

        todoService.createTodoItem(todoItemDto);

        TodoItem todoItem = todoItemRepository.findAll().stream().findFirst().orElseThrow();

        Assertions.assertEquals("Test", todoItem.getBody());
        Assertions.assertEquals(TodoItemStatus.CREATED, todoItem.getStatus());
    }

    @Test
    public void deleteItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setBody("Test");
        todoItem.setStatus(TodoItemStatus.CREATED);
        todoItemRepository.save(todoItem);

        Long expectedId = todoItemRepository.findAll().stream().findFirst().orElseThrow().getId();

        todoService.deleteItem(expectedId);

        Optional<TodoItem> byId = todoItemRepository.findById(expectedId);

        Assertions.assertFalse(byId.isPresent());
    }
}