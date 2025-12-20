package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.UpdateTaskUseCase;
import com.exemplo.stefanini.todo.application.port.in.dto.UpdateTaskCommand;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.exception.TaskNotFoundException;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateTaskService implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task execute(UUID id, UpdateTaskCommand command) {
        Task task = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        if (command.getTitle() != null) task.setTitle(command.getTitle());
        if (command.getDescription() != null) task.setDescription(command.getDescription());
        if (command.getStatus() != null) task.setStatus(command.getStatus());

        return taskRepositoryPort.save(task);
    }

}
