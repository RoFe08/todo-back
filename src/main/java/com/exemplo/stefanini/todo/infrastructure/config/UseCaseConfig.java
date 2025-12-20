package com.exemplo.stefanini.todo.infrastructure.config;

import com.exemplo.stefanini.todo.application.port.in.*;
import com.exemplo.stefanini.todo.application.port.out.TaskRepositoryPort;
import com.exemplo.stefanini.todo.application.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new CreateTaskService(taskRepositoryPort);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new UpdateTaskService(taskRepositoryPort);
    }

    @Bean
    public GetTaskByIdUseCase getTaskByIdUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new GetTaskByIdService(taskRepositoryPort);
    }

    @Bean
    public ListTaskUseCase listTaskUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new ListTaskService(taskRepositoryPort);
    }

    @Bean
    public DeleteTaskByIdUseCase deleteTaskByIdUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new DeleteTaskByIdService(taskRepositoryPort);
    }

}
