package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.application.port.in.dto.UpdateTaskCommand;
import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.UUID;

public interface UpdateTaskUseCase {
    Task execute(UUID id, UpdateTaskCommand command);
}