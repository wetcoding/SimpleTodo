package com.wetcoding.simpletodo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TodoItemList implements Serializable {
    private List<TodoItemDto> items;
}
