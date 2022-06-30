package ru.gretchen.accountant.service;

import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task, User user) {
        taskRepository.save(task);
        taskRepository.addUser(task.getId(), user.getId());
        return task;
    }

    public List<Task> getByDateNow() {
        return taskRepository.getByDate();
    }
}
