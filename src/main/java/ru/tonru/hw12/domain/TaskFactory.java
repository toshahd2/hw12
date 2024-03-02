package ru.tonru.hw12.domain;

import ru.tonru.hw12.entity.Task;

public interface TaskFactory {
    Task createTask(String title);
}