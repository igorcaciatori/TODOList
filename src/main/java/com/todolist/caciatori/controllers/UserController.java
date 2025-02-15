package com.todolist.caciatori.controllers;

import com.todolist.caciatori.dtos.UserRequestDTO;
import com.todolist.caciatori.dtos.UserResponseDTO;
import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.models.User;
import com.todolist.caciatori.services.TaskService;
import com.todolist.caciatori.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public CompletableFuture<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.addUser(userRequestDTO);
    }

    @GetMapping("/get/{id}")
    public CompletableFuture<UserResponseDTO> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get")
    public CompletableFuture<List<UserResponseDTO>> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/update")
    public CompletableFuture<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<Void> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
