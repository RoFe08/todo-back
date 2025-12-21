package com.exemplo.stefanini.todo.adapter.in.web.mapper;

import com.exemplo.stefanini.todo.adapter.in.web.dto.task.TaskResponse;
import com.exemplo.stefanini.todo.domain.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {

    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);

}
