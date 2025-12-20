package com.exemplo.stefanini.todo.adapter.in.web.dto;

import com.exemplo.stefanini.todo.domain.model.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {

    private String title;
    private String description;
    private TaskStatus status;
}
