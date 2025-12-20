package com.exemplo.stefanini.todo.adapter.out.persistence;

import com.exemplo.stefanini.todo.domain.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    TaskJpaEntity toEntity(Task task);

    Task toDomain(TaskJpaEntity entity);
}
