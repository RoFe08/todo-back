package com.exemplo.stefanini.todo.application.port.in.dto;

import com.exemplo.stefanini.todo.domain.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTaskCommand {

    private final String title;
    private final String description;
    private final TaskStatus status;
}