package edu.manager.task_manager.entities.dtos;

import edu.manager.task_manager.entities.enums.TaskStatus;

public record UpdateTaskRequestDto(
        String title,
        String description,
        TaskStatus taskStatus
) {
}
