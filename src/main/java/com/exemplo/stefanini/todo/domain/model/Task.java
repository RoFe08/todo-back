package com.exemplo.stefanini.todo.domain.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Task {

    private final UUID id;
    private final UUID userId;     // ✅ NOVO

    private String title;
    private String description;
    private TaskStatus status;

    private final Instant createdAt;
    private Instant updatedAt;

    public Task(
            UUID id,
            UUID userId,
            String title,
            String description,
            TaskStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId); // ✅ obrigatório
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);

        setTitle(title);
        this.description = description;
        this.status = status != null ? status : TaskStatus.PENDING;
    }

    public static Task newTask(UUID userId, String title, String description) {
        Instant now = Instant.now();
        return new Task(
                UUID.randomUUID(),
                userId,
                title,
                description,
                TaskStatus.PENDING,
                now,
                now
        );
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title is required");
        }
        this.title = title.trim();
        touch();
    }

    public void setDescription(String description) {
        this.description = description;
        touch();
    }

    public void setStatus(TaskStatus status) {
        this.status = Objects.requireNonNull(status);
        touch();
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }
}