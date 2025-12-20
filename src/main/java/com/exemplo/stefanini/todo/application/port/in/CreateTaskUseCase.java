package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.application.port.in.dto.CreateTaskCommand;
import com.exemplo.stefanini.todo.domain.model.Task;

public interface CreateTaskUseCase {
    Task execute(CreateTaskCommand command);
}
