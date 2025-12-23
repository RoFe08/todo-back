package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.CreateTaskUseCase;
import com.exemplo.stefanini.todo.application.port.in.dto.CreateTaskCommand;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task execute(UUID userId, CreateTaskCommand command) {
        Task task = Task.newTask(userId, command.getTitle(), command.getDescription());
        return taskRepositoryPort.save(userId, task);
    }

}
