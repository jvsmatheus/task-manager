package edu.manager.task_manager.entities.responses;

public record ResponseBase<T>(
        boolean status,
        String message,
        T data
) {
}
