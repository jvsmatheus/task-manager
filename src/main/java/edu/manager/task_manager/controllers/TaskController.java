package edu.manager.task_manager.controllers;

import edu.manager.task_manager.entities.Task;
import edu.manager.task_manager.entities.dtos.CreateTaskRequestDto;
import edu.manager.task_manager.entities.dtos.UpdateTaskRequestDto;
import edu.manager.task_manager.entities.responses.ResponseBase;
import edu.manager.task_manager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ResponseBase<List<Task>>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<Task>> findTaskById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<Task>> saveTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(createTaskRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<Task>> updateTask(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        return ResponseEntity.ok(taskService.updateTask(id, updateTaskRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
