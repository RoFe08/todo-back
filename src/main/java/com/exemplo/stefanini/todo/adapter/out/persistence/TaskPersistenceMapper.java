package com.exemplo.stefanini.todo.adapter.out.persistence;

import com.exemplo.stefanini.todo.domain.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    @Mapping(target = "user", ignore = true)
    TaskJpaEntity toEntity(Task task);

    @Mapping(target = "userId", expression = "java(userId(entity))")
    Task toDomain(TaskJpaEntity entity);

    default UUID userId(TaskJpaEntity entity) {
        return entity.getUser() != null ? entity.getUser().getId() : null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget TaskJpaEntity target, Task source);
}
