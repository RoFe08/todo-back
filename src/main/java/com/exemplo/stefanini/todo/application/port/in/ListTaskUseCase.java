package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.List;
import java.util.UUID;

public interface ListTaskUseCase {
    List<Task> execute(UUID userId);
}
