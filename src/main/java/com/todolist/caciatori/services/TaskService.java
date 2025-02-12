package com.todolist.caciatori.services;

import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Async
    public CompletableFuture<Task> addTask(Task task) {
        try {
            Task savedTask = taskRepository.save(task);
            return CompletableFuture.completedFuture(savedTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<Task> getTaskById(long id) {
        return CompletableFuture.supplyAsync(() -> taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found")));
    }

    @Async
    public CompletableFuture<List<Task>> getTasks() {
        return CompletableFuture.supplyAsync(() -> taskRepository.findAll());
    }

    @Async
    public CompletableFuture<Void> deleteTaskById(Long id) {
        return CompletableFuture.runAsync(() -> taskRepository.deleteById(id));
    }

    @Async
    public CompletableFuture<Task> updateTask(Task task) {
        return CompletableFuture.supplyAsync(() -> taskRepository.save(task));
    }
}
