package com.wetcoding.simpletodo.service.bean;

import com.wetcoding.simpletodo.repository.TodoItemRepository;
import com.wetcoding.simpletodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoServiceBean implements TodoService {
    private final TodoItemRepository todoItemRepository;
}
