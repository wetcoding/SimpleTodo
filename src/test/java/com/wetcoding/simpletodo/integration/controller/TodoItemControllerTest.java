package com.wetcoding.simpletodo.integration.controller;

import com.wetcoding.simpletodo.dto.TodoItemDto;
import com.wetcoding.simpletodo.integration.AbstractIntegrationTest;
import com.wetcoding.simpletodo.model.TodoItem;
import com.wetcoding.simpletodo.model.enums.TodoItemStatus;
import com.wetcoding.simpletodo.repository.TodoItemRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoItemControllerTest extends AbstractIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoItemRepository todoItemRepository;

    public Long id;

    @BeforeAll
    public void init() {
        TodoItem todoItem = new TodoItem();
        todoItem.setBody("Test");
        todoItem.setStatus(TodoItemStatus.CREATED);
        id = todoItemRepository.save(todoItem).getId();
    }

    @AfterAll
    public void clean() {
        todoItemRepository.deleteAll();
    }

    @Test
    public void getItem() {
        TodoItemDto response = restTemplate.getForObject("/todo-item/" + id, TodoItemDto.class);

        Assertions.assertEquals("Test", response.getBody());
        Assertions.assertEquals(TodoItemStatus.CREATED, response.getStatus());
    }
}
