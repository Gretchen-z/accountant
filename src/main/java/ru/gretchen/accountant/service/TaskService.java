package ru.gretchen.accountant.service;

import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        taskRepository.save(task);
        return task;
    }

    public List<Task> getByDateNow() {
        return taskRepository.getByDate();
    }
}
