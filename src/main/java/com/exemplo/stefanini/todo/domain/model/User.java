package com.exemplo.stefanini.todo.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class User {
    private UUID id;
    private String name;
    private String email;
    private String passwordHash;
    private Instant createdAt;
    private Instant updatedAt;
}
