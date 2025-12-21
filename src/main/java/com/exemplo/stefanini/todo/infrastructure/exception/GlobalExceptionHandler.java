package com.exemplo.stefanini.todo.infrastructure.exception;

import com.exemplo.stefanini.todo.domain.exception.EmailAlreadyUsedException;
import com.exemplo.stefanini.todo.domain.exception.InvalidCredentialsException;
import com.exemplo.stefanini.todo.domain.exception.TaskNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFound(
            TaskNotFoundException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFound(
            EmailAlreadyUsedException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFound(
            InvalidCredentialsException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        List<Map<String, Object>> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toFieldError)
                .toList();

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                request.getRequestURI(),
                fieldErrors
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        // Em produção, você pode logar ex aqui com logger
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error",
                request.getRequestURI(),
                null
        );
    }

    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status,
            String message,
            String path,
            List<Map<String, Object>> errors
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);

        if (errors != null && !errors.isEmpty()) {
            body.put("errors", errors);
        }

        return ResponseEntity.status(status).body(body);
    }

    private Map<String, Object> toFieldError(FieldError fe) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("field", fe.getField());
        m.put("rejectedValue", fe.getRejectedValue());
        m.put("message", fe.getDefaultMessage());
        return m;
    }
}
