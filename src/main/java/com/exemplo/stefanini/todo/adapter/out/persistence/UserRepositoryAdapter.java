package com.exemplo.stefanini.todo.adapter.out.persistence;

import com.exemplo.stefanini.todo.application.port.out.UserRepositoryPort;
import com.exemplo.stefanini.todo.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserSpringDataRepository repo;
    private final UserPersistenceMapper mapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserJpaEntity saved = repo.save(mapper.toJpa(user));
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}