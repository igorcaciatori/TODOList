package com.todolist.caciatori.dtos;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for the User entity.
 * This DTO the user will send to the system.
 * The user doesn't have access to the ID.
 */
@Data
public class UserRequestDTO {
        private String name;
        private String email;
        private String password;
}
