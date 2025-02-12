package com.todolist.caciatori.controllers;

import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.services.TaskService;
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
    public CompletableFuture<Task> createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/get/{id}")
    public CompletableFuture<Task> getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/get")
    public CompletableFuture<List<Task>> getTasks() {
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
