package com.exemplo.stefanini.todo.application.port.out;

import com.exemplo.stefanini.todo.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(UUID id);

    List<Task> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
