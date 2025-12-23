package com.exemplo.stefanini.todo.application.port.in;

import java.util.UUID;

public interface DeleteTaskByIdUseCase {
    void execute(UUID userId, UUID taskId);
}
