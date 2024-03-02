package ru.tonru.hw12.service;

import org.springframework.stereotype.Service;
import ru.tonru.hw12.domain.*;
import ru.tonru.hw12.entity.Task;
import ru.tonru.hw12.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TaskServiceFacadeImpl implements TaskServiceFacade {
    private final TaskFactory urgentTaskFactory;
    private final List<TaskObserver> observers = new CopyOnWriteArrayList<>();
    private final TaskFactory usualTaskFactory;
    private final TaskRepository taskRepository;

    public TaskServiceFacadeImpl(TaskUrgent urgentTaskFactory, TaskUsual usualTaskFactory, TaskRepository taskRepository) {
        this.urgentTaskFactory = urgentTaskFactory;
        this.usualTaskFactory = usualTaskFactory;
        this.taskRepository = taskRepository;
    }

    @Override
    public void createObserver(TaskObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Task taskUpdate) {
        for (TaskObserver observer : observers) {
            observer.update(taskUpdate);
        }
    }

    @Override
    public Task createTask(Task task) {
        Task newTask;
        if (task.isUrgent()) {
            newTask = urgentTaskFactory.createTask(task.getTitle()).setCompleted(task.isCompleted());
        } else {
            newTask = usualTaskFactory.createTask(task.getTitle()).setCompleted(task.isCompleted());
        }
        taskRepository.save(newTask);
        notifyObservers(newTask);
        return newTask;
    }

    @Override
    public Task updateTask(Long id, String title, boolean completed, boolean urgency) {
        Task task = getTask(id);
        if (task == null) {
            return null;
        }
        task.setTitle(title).setCompleted(completed).setUrgency(urgency);
        taskRepository.save(task);
        notifyObservers(task);
        return task;
    }

    @Override
    public boolean deleteTask(Long id) {
        Task task = getTask(id);
        if (task == null) {
            return false;
        }
        taskRepository.deleteById(id);
        notifyObservers(task);
        return true;
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}