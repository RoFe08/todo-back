package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.CreateTaskUseCase;
import com.exemplo.stefanini.todo.application.port.in.dto.CreateTaskCommand;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task execute(CreateTaskCommand command) {
        Task task = Task.newTask(command.getTitle(), command.getDescription());
        return taskRepositoryPort.save(task);
    }

}
