package io.github.mat3e.controller;

import io.github.mat3e.model.Task;
import io.github.mat3e.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }
    @GetMapping(value = "/tasks", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Task>> readAllTasks() {
        logger.warn("Exposing all the tasks!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.info("Custom pager.");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @PutMapping(value = "/tasks/{id}")
    ResponseEntity<?> updateTask(@PathVariable("id") int taskId, @RequestBody @Valid Task toUpdate){
        if(!repository.existsById(taskId)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(taskId)
                .ifPresent(task -> {
                    task.updateFrom(toUpdate);
                    repository.save(task);
                });
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping(value = "/tasks/{id}")
    public ResponseEntity<?> toggleTask(@PathVariable("id") int taskId){
        if(!repository.existsById(taskId)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(taskId)
                .ifPresent(task -> task.setDone(!task.isDone()));
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/tasks")
    ResponseEntity<?> writeTask(@RequestBody @Valid Task toWrite){
        Task result = repository.save(toWrite);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
