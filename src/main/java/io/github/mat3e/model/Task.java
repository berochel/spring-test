package io.github.mat3e.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

// TODO: stub test task to do
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "desc")
    @NotBlank(message = "Task's description must be not null.")
    private String description;
    @Column(name = "done")
    private boolean done;

    Task()
    {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
