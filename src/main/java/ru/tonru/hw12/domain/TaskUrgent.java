package ru.tonru.hw12.domain;

import org.springframework.stereotype.Service;
import ru.tonru.hw12.entity.Task;

@Service
public class TaskUrgent implements TaskFactory {
    @Override
    public Task createTask(String title) {
        return new Task().setUrgency(true).setTitle(title);
    }
}