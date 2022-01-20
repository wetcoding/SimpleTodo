package com.wetcoding.simpletodo.service;

import com.wetcoding.simpletodo.dto.TodoItemDto;

import java.util.List;

public interface TodoService {
    List<TodoItemDto> getAllItems();

    TodoItemDto getItem(Long id);

    void updateItem(Long id, String text);

    void deleteItem(Long id);

    void createTodoItem(TodoItemDto todoItemDto);
}
