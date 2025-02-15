package com.todolist.caciatori.dtos;

import lombok.Data;

/**
 * This is the DTO that the system will send to the user as a response.
 * This DTO doesn't have the password for security reasons.
 */
@Data
public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
}
