package com.exemplo.stefanini.todo.adapter.in.web.dto;

import com.exemplo.stefanini.todo.domain.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TaskResponse {

    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private Instant createdAt;
}