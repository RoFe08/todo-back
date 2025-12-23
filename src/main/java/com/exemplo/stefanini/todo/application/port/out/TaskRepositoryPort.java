package com.exemplo.stefanini.todo.application.port.out;

import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {

    Task save(UUID userId, Task task);

    Optional<Task> findById(UUID userId, UUID taskId);

    List<Task> findAll(UUID userId);

    boolean existsById(UUID userId, UUID taskId);

    void deleteById(UUID userId, UUID taskId);
}

