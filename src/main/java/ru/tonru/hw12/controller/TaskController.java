package ru.tonru.hw12.controller;

import org.springframework.http.ResponseEntity;
import ru.tonru.hw12.entity.*;
import java.util.List;

public interface TaskController {
    ResponseEntity<Task> createTask(Task task);

    ResponseEntity<Task> updateTask(Task task, Long id);

    boolean deleteTask(Long id);

    ResponseEntity<Task> getTask(Long id);

    List<Task> getTasks();
}