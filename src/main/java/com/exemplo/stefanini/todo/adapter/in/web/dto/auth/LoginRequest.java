package com.exemplo.stefanini.todo.adapter.in.web.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String email;
    private String password;
}
