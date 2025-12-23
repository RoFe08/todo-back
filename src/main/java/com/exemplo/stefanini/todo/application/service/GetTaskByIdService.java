package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.GetTaskByIdUseCase;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.exception.TaskNotFoundException;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetTaskByIdService implements GetTaskByIdUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task execute(UUID userId, UUID taskId) {
        return taskRepositoryPort.findById(userId, taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

}
