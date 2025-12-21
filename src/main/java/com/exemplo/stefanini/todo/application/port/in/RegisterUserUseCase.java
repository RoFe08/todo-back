package com.exemplo.stefanini.todo.application.port.in;

import com.exemplo.stefanini.todo.domain.model.User;

public interface RegisterUserUseCase {
    User register(String name, String email, String rawPassword);
}