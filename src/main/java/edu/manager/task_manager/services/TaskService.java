package edu.manager.task_manager.services;

import edu.manager.task_manager.entities.Task;
import edu.manager.task_manager.entities.dtos.CreateTaskRequestDto;
import edu.manager.task_manager.entities.dtos.UpdateTaskRequestDto;
import edu.manager.task_manager.entities.responses.ResponseBase;
import edu.manager.task_manager.exceptions.TaskNotFoundException;
import edu.manager.task_manager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseBase<List<Task>> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return new ResponseBase<List<Task>>(true, "Sucesso ao buscar tarefas", tasks);
    }

    public ResponseBase<Task> findTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        return new ResponseBase<Task>(true, "Sucesso ao buscar tarefa", task);
    }

    public ResponseBase<Task> saveTask(CreateTaskRequestDto createTaskRequestDto) {
        var task = new Task();

        task.setTitle(createTaskRequestDto.title());
        task.setDescription(createTaskRequestDto.description());
        task.setTaskStatus(createTaskRequestDto.taskStatus());

        return new ResponseBase<Task>(true, "Sucesso ao cadastrar tarefa", taskRepository.save(task));
    }

    public ResponseBase<Task> updateTask(Long id, UpdateTaskRequestDto updateTaskRequestDto) {
        Task updateTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        updateTask.setTitle(updateTaskRequestDto.title());
        updateTask.setDescription(updateTaskRequestDto.description());
        updateTask.setTaskStatus(updateTaskRequestDto.taskStatus());

        var updatedTask = taskRepository.save(updateTask);

        return new ResponseBase<Task>(true, "Sucesso ao modificar tarefa", updatedTask);
    }

    public ResponseBase<Void> deleteTask(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.deleteById(id);

        return new ResponseBase<Void>(true, "Sucesso ao remover tarefa", null);
    }
}
