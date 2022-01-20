package com.wetcoding.simpletodo.controller;

import com.wetcoding.simpletodo.dto.TodoItemDto;
import com.wetcoding.simpletodo.mapper.TodoItemMapper;
import com.wetcoding.simpletodo.service.TodoService;
import com.wetcoding.simpletodo.service.bean.UserServiceBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo-item")
public class TodoListController {
    private final TodoService todoService;


    @GetMapping
    public List<TodoItemDto> getTodoItemList() {
        return todoService.getAllItems();
    }

    @GetMapping("/{id}")
    public TodoItemDto getTodoItem(@PathVariable("id") Long id) {
        return todoService.getItem(id);
    }

    @PostMapping()
    public ResponseEntity createTodoItem(@RequestBody TodoItemDto todoItemDto) {
        todoService.createTodoItem(todoItemDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity updateTodoItem(@PathVariable("id") Long id, String text) {
        todoService.updateItem(id, text);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoItem(@PathVariable("id") Long id) {
        todoService.deleteItem(id);

        return ResponseEntity.ok().build();
    }
}
