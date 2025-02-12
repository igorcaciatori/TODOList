package com.todolist.caciatori.services;

import com.todolist.caciatori.models.Task;
import com.todolist.caciatori.models.User;
import com.todolist.caciatori.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Async
    public CompletableFuture<User> addUser(User user) {
        try {
            User savedUser = userRepository.save(user);
            return CompletableFuture.completedFuture(savedUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Async
    public CompletableFuture<User> getUserById(long id) {
        return  CompletableFuture.supplyAsync(() -> userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Async
    public CompletableFuture<List<User>> getUsers() {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll());
    }

    @Async
    public CompletableFuture<User> updateUser(User user) {
        return CompletableFuture.completedFuture(userRepository.save(user));
    }

    @Async
    public CompletableFuture<Void> deleteUser(long id) {
        return CompletableFuture.runAsync(() -> {userRepository.deleteById(id);});
    }
}
