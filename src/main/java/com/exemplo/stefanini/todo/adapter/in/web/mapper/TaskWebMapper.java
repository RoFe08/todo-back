package com.exemplo.stefanini.todo.adapter.in.web.mapper;

import com.exemplo.stefanini.todo.adapter.in.web.dto.task.CreateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.dto.task.UpdateTaskRequest;
import com.exemplo.stefanini.todo.application.port.in.dto.CreateTaskCommand;
import com.exemplo.stefanini.todo.application.port.in.dto.UpdateTaskCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskWebMapper {

    CreateTaskCommand toCommand(CreateTaskRequest request);

    UpdateTaskCommand toCommand(UpdateTaskRequest request);
}

