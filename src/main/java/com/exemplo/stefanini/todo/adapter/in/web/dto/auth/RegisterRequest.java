package com.exemplo.stefanini.todo.adapter.in.web.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}