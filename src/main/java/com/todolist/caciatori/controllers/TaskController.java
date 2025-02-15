package com.todolist.caciatori.controllers;

import com.todolist.caciatori.dtos.TaskDTO;
import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/create")
    @Operation(summary = "Creates a task", description = "Returns the DTO of the task created")
    public CompletableFuture<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.addTask(taskDTO);
    }

    @GetMapping("/get/{id}")
    public CompletableFuture<TaskDTO> getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/get")
    public CompletableFuture<List<TaskDTO>> getTasks() {
        return taskService.getTasks();
    }

    @PutMapping("/update")
    public CompletableFuture<Task> updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<Void> deleteTaskById(@PathVariable long id) {
        return taskService.deleteTaskById(id);
    }
}
