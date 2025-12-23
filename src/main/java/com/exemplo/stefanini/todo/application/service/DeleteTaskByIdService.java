package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.DeleteTaskByIdUseCase;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteTaskByIdService implements DeleteTaskByIdUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public void execute(UUID userId, UUID taskId) {
        if (!taskRepositoryPort.existsById(userId, taskId)) {
            throw new TaskNotFoundException(taskId);
        }
        taskRepositoryPort.deleteById(userId, taskId);
    }
}