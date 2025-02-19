package com.todolist.caciatori.services;

import com.todolist.caciatori.dtos.TaskDTO;
import com.todolist.caciatori.mappers.UserMapper;
import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.todolist.caciatori.models.User;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Async
    public CompletableFuture<TaskDTO> addTask(TaskDTO taskDTO) {
        try {
            User user = userService.getUserEntityById(taskDTO.getUser().getId()).join();
            Task task = new Task();
            task.setId(taskDTO.getId());
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setStatus(taskDTO.isStatus());
            task.setPriority(taskDTO.getPriority());
            task.setUser(user);

            Task savedTask = taskRepository.save(task);

            return CompletableFuture.completedFuture(convertToDTO(savedTask));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<TaskDTO> getTaskById(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
                return convertToDTO(task);
            } catch (ResponseStatusException e) {
                System.out.println(e.getMessage());
                return null;
            }
        });
    }

    @Async
    public CompletableFuture<List<TaskDTO>> getTasks() {
        return CompletableFuture.supplyAsync(() -> taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
    }

    @Async
    public CompletableFuture<Task> updateTask(Task task) {
        return CompletableFuture.supplyAsync(() -> taskRepository.save(task));
    }

    @Async
    public CompletableFuture<Void> deleteTaskById(long id) {
        return CompletableFuture.runAsync(() -> taskRepository.deleteById(id));
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.isStatus());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setUser(UserMapper.toDTO(task.getUser()));
        return taskDTO;
    }
}
