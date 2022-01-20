package com.wetcoding.simpletodo.mapper;

import com.wetcoding.simpletodo.dto.TodoItemDto;
import com.wetcoding.simpletodo.model.TodoItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface TodoItemMapper {
    TodoItemDto toDto(TodoItem entity);

    List<TodoItemDto> toDtoList(List<TodoItem> entityList);

    TodoItem fromDto(TodoItemDto dto);
}
