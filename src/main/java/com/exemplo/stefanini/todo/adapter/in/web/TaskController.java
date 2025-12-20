package com.exemplo.stefanini.todo.adapter.in.web;

import com.exemplo.stefanini.todo.adapter.in.web.dto.CreateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.dto.TaskResponse;
import com.exemplo.stefanini.todo.adapter.in.web.dto.UpdateTaskRequest;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskDtoMapper;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskResponseMapper;
import com.exemplo.stefanini.todo.adapter.in.web.mapper.TaskWebMapper;
import com.exemplo.stefanini.todo.application.port.in.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final DeleteTaskByIdUseCase deleteTaskByIdUseCase;

    private final TaskWebMapper taskWebMapper;
    private final TaskResponseMapper taskResponseMapper;

    // POST /tasks
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        return taskResponseMapper.toResponse(
                createTaskUseCase.execute(
                        taskWebMapper.toCommand(request)
                )
        );
    }

    // GET /tasks
    @GetMapping
    public List<TaskResponse> listAll() {
        return taskResponseMapper.toResponseList(
                listTaskUseCase.execute()
        );
    }

    // GET /tasks/{id}
    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable UUID id) {
        return taskResponseMapper.toResponse(
                getTaskByIdUseCase.execute(id)
        );
    }

    // PUT /tasks/{id}
    @PutMapping("/{id}")
    public TaskResponse update(
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

    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        deleteTaskByIdUseCase.execute(id);
    }
}
