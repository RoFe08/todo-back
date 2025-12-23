package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.UUID;

public interface GetTaskByIdUseCase {
    Task execute(UUID userId, UUID taskId);
}
