package com.exemplo.stefanini.todo.domain.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Task {

    private final UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private final Instant createdAt;

    public Task(UUID id, String title, String description, TaskStatus status, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.createdAt = Objects.requireNonNull(createdAt);
        setTitle(title);
        this.description = description;
        this.status = status != null ? status : TaskStatus.PENDING;
    }

    public static Task newTask(String title, String description) {
        return new Task(
                UUID.randomUUID(),
                title,
                description,
                TaskStatus.PENDING,
                Instant.now()
        );
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title is required");
        }
        this.title = title.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = Objects.requireNonNull(status);
    }
}
