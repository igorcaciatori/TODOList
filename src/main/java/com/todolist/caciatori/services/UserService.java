package com.todolist.caciatori.services;

import com.todolist.caciatori.dtos.UserRequestDTO;
import com.todolist.caciatori.dtos.UserResponseDTO;
import com.todolist.caciatori.mappers.UserMapper;
import com.todolist.caciatori.models.User;
import com.todolist.caciatori.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Async
    public CompletableFuture<UserResponseDTO> addUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(user);
        return CompletableFuture.completedFuture(UserMapper.toDTO(savedUser));
    }

    @Async
    public CompletableFuture<UserResponseDTO> getUserById(long id) {
        return  CompletableFuture.supplyAsync(() -> userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Async
    public CompletableFuture<List<UserResponseDTO>> getUsers() {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @Async
    public CompletableFuture<User> updateUser(User user) {
        return CompletableFuture.completedFuture(userRepository.save(user));
    }

    @Async
    public CompletableFuture<Void> deleteUser(long id) {
        return CompletableFuture.runAsync(() -> userRepository.deleteById(id));
    }

    @Async
    public CompletableFuture<User> getUserEntityById(long id) {
        return CompletableFuture.supplyAsync(() -> userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

}
