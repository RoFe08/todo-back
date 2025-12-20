package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.GetTaskByIdUseCase;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.exception.TaskNotFoundException;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetTaskByIdService implements GetTaskByIdUseCase {

    private final TaskRepositoryPort repository;

    @Override
    public Task execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

}
