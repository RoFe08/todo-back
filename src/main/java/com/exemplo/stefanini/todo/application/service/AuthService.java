package com.exemplo.stefanini.todo.application.service;


import com.exemplo.stefanini.todo.application.port.in.LoginUseCase;
import com.exemplo.stefanini.todo.application.port.in.RegisterUserUseCase;
import com.exemplo.stefanini.todo.application.port.out.UserRepositoryPort;
import com.exemplo.stefanini.todo.domain.model.User;
import com.exemplo.stefanini.todo.domain.exception.EmailAlreadyUsedException;
import com.exemplo.stefanini.todo.domain.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements RegisterUserUseCase, LoginUseCase {

    private final UserRepositoryPort userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Override
    public User register(String name, String email, String rawPassword) {
        if (userRepo.existsByEmail(email)) {
            throw new EmailAlreadyUsedException();
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(encoder.encode(rawPassword))
                .build();

        return userRepo.save(user);
    }

    @Override
    public String login(String email, String rawPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        if (!encoder.matches(rawPassword, user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return jwtService.generateToken(user.getId().toString(), user.getEmail(), user.getName());
    }
}