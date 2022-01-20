package com.wetcoding.simpletodo.service.bean;

import com.wetcoding.simpletodo.dto.TodoItemDto;
import com.wetcoding.simpletodo.mapper.TodoItemMapper;
import com.wetcoding.simpletodo.model.TodoItem;
import com.wetcoding.simpletodo.model.enums.TodoItemStatus;
import com.wetcoding.simpletodo.repository.TodoItemRepository;
import com.wetcoding.simpletodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceBean implements TodoService {
    private final TodoItemRepository todoItemRepository;
    private final TodoItemMapper todoItemMapper;

    @Override
    public List<TodoItemDto> getAllItems() {
        return todoItemMapper.toDtoList(todoItemRepository.findAll());
    }

    @Override
    public TodoItemDto getItem(Long id) {
        return todoItemMapper.toDto(todoItemRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public void updateItem(Long id, String text) {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow();

        todoItem.setBody(text);
        todoItemRepository.save(todoItem);
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow();

        todoItemRepository.delete(todoItem);
        todoItemRepository.flush();
    }

    @Override
    @Transactional
    public void createTodoItem(TodoItemDto todoItemDto) {
        TodoItem todoItem = todoItemMapper.fromDto(todoItemDto);
        todoItem.setStatus(TodoItemStatus.CREATED);
        todoItemRepository.save(todoItem);
    }
}
