package ru.tonru.hw12.domain;

import ru.tonru.hw12.entity.Task;
public interface TaskObserver {
    void update(Task task);
}
