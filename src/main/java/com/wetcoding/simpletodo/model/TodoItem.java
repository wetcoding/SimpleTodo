package com.wetcoding.simpletodo.model;

import com.wetcoding.simpletodo.model.enums.TodoItemStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class TodoItem extends BaseEntity{
    private String body;
    @Enumerated(EnumType.STRING)
    private TodoItemStatus status;
}
