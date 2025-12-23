package com.exemplo.stefanini.todo.adapter.out.persistence;

import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.domain.exception.TaskNotFoundException;
import com.exemplo.stefanini.todo.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringDataTaskRepository taskRepo;
    private final UserJpaRepository userRepo; // você precisa criar esse repo
    private final TaskPersistenceMapper mapper;

    @Override
    public Task save(UUID userId, Task task) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found: " + userId));

        var entity = mapper.toEntity(task);
        entity.setUser(user);

        var saved = taskRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Task> findById(UUID userId, UUID taskId) {
        return taskRepo.findByIdAndUser_Id(taskId, userId).map(mapper::toDomain);
    }

    @Override
    public List<Task> findAll(UUID userId) {
        return taskRepo.findAllByUser_Id(userId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(UUID userId, UUID taskId) {
        return taskRepo.findByIdAndUser_Id(taskId, userId).isPresent();
    }

    @Override
    public void deleteById(UUID userId, UUID taskId) {
        var entity = taskRepo.findByIdAndUser_Id(taskId, userId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        taskRepo.delete(entity);
    }
}
