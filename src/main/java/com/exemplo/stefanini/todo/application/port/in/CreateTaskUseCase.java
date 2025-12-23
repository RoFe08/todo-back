package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.application.port.in.dto.CreateTaskCommand;
import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.UUID;

public interface CreateTaskUseCase {
    Task execute(UUID userId, CreateTaskCommand command);
}
