package com.exemplo.stefanini.todo.application.service;

import com.exemplo.stefanini.todo.application.port.in.ListTaskUseCase;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListTaskService implements ListTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public List<Task> execute() {
        return taskRepositoryPort.findAll();
    }

}
