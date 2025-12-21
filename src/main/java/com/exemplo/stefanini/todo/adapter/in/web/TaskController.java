package com.exemplo.stefanini.todo.adapter.in.web;

import com.exemplo.stefanini.todo.adapter.in.web.dto.CreateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.dto.TaskResponse;
import com.exemplo.stefanini.todo.adapter.in.web.dto.UpdateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskResponseMapper;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskWebMapper;
import com.exemplo.stefanini.todo.application.port.in.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Create a new task")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(
            @Valid @RequestBody CreateTaskRequest request
    ) {
        return taskResponseMapper.toResponse(
                createTaskUseCase.execute(
                        taskWebMapper.toCommand(request)
                )
        );
    }

    @Operation(summary = "List all tasks")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    @GetMapping
    public List<TaskResponse> listAll() {
        return taskResponseMapper.toResponseList(
                listTaskUseCase.execute()
        );
    }

    @Operation(summary = "Get task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public TaskResponse getById(
            @Parameter(description = "Task ID", required = true)
            @PathVariable UUID id
    ) {
        return taskResponseMapper.toResponse(
                getTaskByIdUseCase.execute(id)
        );
    }

    @Operation(summary = "Update an existing task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{id}")
    public TaskResponse update(
            @Parameter(description = "Task ID", required = true)
            @PathVariable UUID id,
            @RequestBody UpdateTaskRequest request
    ) {
        return taskResponseMapper.toResponse(
                updateTaskUseCase.execute(
                        id,
                        taskWebMapper.toCommand(request)
                )
        );
    }

    @Operation(summary = "Delete task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Task ID", required = true)
            @PathVariable UUID id
    ) {
        deleteTaskByIdUseCase.execute(id);
    }
}
