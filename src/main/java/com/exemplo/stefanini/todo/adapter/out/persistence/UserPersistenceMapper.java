package com.exemplo.stefanini.todo.adapter.out.persistence;

import com.exemplo.stefanini.todo.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toDomain(UserJpaEntity entity);

    @Mapping(target = "passwordHash", source = "passwordHash")
    UserJpaEntity toJpa(User domain);
}