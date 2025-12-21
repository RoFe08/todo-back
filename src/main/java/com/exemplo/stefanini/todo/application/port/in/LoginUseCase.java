package com.exemplo.stefanini.todo.application.port.in;

public interface LoginUseCase {
    String login(String email, String rawPassword);
}