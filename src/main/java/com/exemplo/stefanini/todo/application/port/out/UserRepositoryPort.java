package com.exemplo.stefanini.todo.application.port.out;

import com.exemplo.stefanini.todo.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    User save(User user);
    boolean existsByEmail(String email);
}