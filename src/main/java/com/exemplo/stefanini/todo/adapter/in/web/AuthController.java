package com.exemplo.stefanini.todo.adapter.in.web;

import com.exemplo.stefanini.todo.adapter.in.web.dto.auth.AuthResponse;
import com.exemplo.stefanini.todo.adapter.in.web.dto.auth.LoginRequest;
import com.exemplo.stefanini.todo.adapter.in.web.dto.auth.RegisterRequest;
import com.exemplo.stefanini.todo.application.port.in.LoginUseCase;
import com.exemplo.stefanini.todo.application.port.in.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest req) {
        registerUseCase.register(req.getName(), req.getEmail(), req.getPassword());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        String token = loginUseCase.login(req.getEmail(), req.getPassword());
        return new AuthResponse(token);
    }
}
