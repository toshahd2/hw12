package ru.tonru.hw12.domain;

import ru.tonru.hw12.entity.Task;

public interface TaskSubject {
    void createObserver(TaskObserver observer);

    void deleteObserver(TaskObserver observer);

    void notifyObservers(Task event);
}