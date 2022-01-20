package com.wetcoding.simpletodo.dto;

import com.wetcoding.simpletodo.model.enums.TodoItemStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class TodoItemDto implements Serializable {
    private Long id;
    private String body;
    private TodoItemStatus status;
}
