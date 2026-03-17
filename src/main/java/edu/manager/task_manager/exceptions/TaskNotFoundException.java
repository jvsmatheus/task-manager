package edu.manager.task_manager.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Tarefa de id: " + id + "não encontrada");
    }
}
