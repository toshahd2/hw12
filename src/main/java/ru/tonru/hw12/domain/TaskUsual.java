package ru.tonru.hw12.domain;

import org.springframework.stereotype.Repository;
import ru.tonru.hw12.entity.Task;

@Repository
public class TaskUsual implements TaskFactory {
    @Override
    public Task createTask(String title) {
        return new Task().setUrgency(false).setTitle(title);
    }
}