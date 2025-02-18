package com.todolist.caciatori.controllers;

import com.todolist.caciatori.dtos.TaskDTO;
import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucessfully creates a new task in the database and returns it")
    })
    public CompletableFuture<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.addTask(taskDTO);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get one task", description = "Returns one task based on the id that is in the path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns sucessfully the task")
    })
    public CompletableFuture<TaskDTO> getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/get")
    @Operation(summary = "Get for all tasks", description = "Returns all tasks that are in the database. " +
            "If there are no tasks, it will still return 200 (OK), since it is still getting all the tasks")
    @ApiResponse(responseCode = "200", description = "Returns all the tasks")
    public CompletableFuture<List<TaskDTO>> getTasks() {
        return taskService.getTasks();
    }

    @PutMapping("/update")
    @Operation(summary = "Updates a task", description = "Updates a task based on its ID that is sent within the body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates the task sucessfully, and returns it (updated)")

    })
    public CompletableFuture<Task> updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a task", description = "Deletes a task based on the ID passed on the path")
    @ApiResponse(responseCode = "200", description = "Deletes sucessfully a task. Even if a task with this ID doesn't exist," +
            "it'll return 200 (it still deletes, even without it existing)")
    public CompletableFuture<Void> deleteTaskById(@PathVariable long id) {
        return taskService.deleteTaskById(id);
    }
}
