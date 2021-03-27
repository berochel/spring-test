package io.github.mat3e.model;

import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: stub test task to do
@Entity
@Table(name = "tasks")
public class Task {
    private int id;
    private String description;
    private boolean done;
}
