package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.List;

public interface ListTaskUseCase {
    List<Task> execute();
}
