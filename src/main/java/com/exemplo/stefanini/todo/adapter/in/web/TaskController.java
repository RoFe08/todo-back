package com.exemplo.stefanini.todo.adapter.in.web;

import com.exemplo.stefanini.todo.adapter.in.web.dto.task.CreateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.dto.task.TaskResponse;
import com.exemplo.stefanini.todo.adapter.in.web.dto.task.UpdateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskResponseMapper;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskWebMapper;
import com.exemplo.stefanini.todo.application.port.in.CreateTaskUseCase;
import com.exemplo.stefanini.todo.application.port.in.DeleteTaskByIdUseCase;
import com.exemplo.stefanini.todo.application.port.in.GetTaskByIdUseCase;
import com.exemplo.stefanini.todo.application.port.in.ListTaskUseCase;
import com.exemplo.stefanini.todo.application.port.in.UpdateTaskUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Task management operations")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final DeleteTaskByIdUseCase deleteTaskByIdUseCase;

    private final TaskWebMapper taskWebMapper;
    private final TaskResponseMapper taskResponseMapper;

    private UUID userIdFrom(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }

    @Operation(summary = "Create a new task")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateTaskRequest request
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        var task = createTaskUseCase.execute(userId, taskWebMapper.toCommand(request));
        return taskResponseMapper.toResponse(task);
    }

    @Operation(summary = "List tasks for current user")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    @GetMapping
    public List<TaskResponse> listAll(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = userIdFrom(jwt);

        return taskResponseMapper.toResponseList(
                listTaskUseCase.execute(userId)
        );
    }

    @Operation(summary = "Get task by id (only if belongs to current user)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public TaskResponse getById(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "Task ID", required = true)
            @PathVariable UUID id
    ) {
        UUID userId = userIdFrom(jwt);

        return taskResponseMapper.toResponse(
                getTaskByIdUseCase.execute(userId, id)
        );
    }

    @Operation(summary = "Update an existing task (only if belongs to current user)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskRequest request
    ) {
        UUID userId = userIdFrom(jwt);

        var task = updateTaskUseCase.execute(userId, id, taskWebMapper.toCommand(request));
        return ResponseEntity.ok(taskResponseMapper.toResponse(task));
    }

    @Operation(summary = "Delete task by id (only if belongs to current user)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "Task ID", required = true)
            @PathVariable UUID id
    ) {
        UUID userId = userIdFrom(jwt);
        deleteTaskByIdUseCase.execute(userId, id);
    }
}
