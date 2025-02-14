package com.todolist.caciatori.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @NonNull
    @Column
    private String name;

    @NonNull
    @Column
    private String password;

    @NonNull
    @Column
    private String email;
}
