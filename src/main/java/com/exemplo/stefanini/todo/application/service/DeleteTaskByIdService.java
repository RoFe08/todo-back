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
    public void execute(UUID id) {
        if (!taskRepositoryPort.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepositoryPort.deleteById(id);
    }
}