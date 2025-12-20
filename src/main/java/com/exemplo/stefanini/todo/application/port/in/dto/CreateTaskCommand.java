package com.exemplo.stefanini.todo.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTaskCommand {

    private final String title;
    private final String description;
}
