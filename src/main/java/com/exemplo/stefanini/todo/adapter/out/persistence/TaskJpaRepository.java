package com.exemplo.stefanini.todo.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, UUID> {
    Optional<TaskJpaEntity> findByIdAndUser_Id(UUID id, UUID userId);
    List<TaskJpaEntity> findAllByUser_Id(UUID userId);
}