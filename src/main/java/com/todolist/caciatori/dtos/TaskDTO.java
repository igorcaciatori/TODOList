package com.todolist.caciatori.dtos;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for the Task entity.
 * This DTO is used to avoid transferring the full User entity.
 * this DTO only has the User ID.
 */
@Data
public class TaskDTO {
    private int id;
    private String title;
    private String description;
    private boolean status;
    private int priority;
    private UserResponseDTO user;
}
