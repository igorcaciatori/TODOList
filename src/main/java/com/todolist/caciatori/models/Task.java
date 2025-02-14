package com.todolist.caciatori.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean status;

    @Column
    private int priority;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @NonNull
    @Column
    private User user;
}
