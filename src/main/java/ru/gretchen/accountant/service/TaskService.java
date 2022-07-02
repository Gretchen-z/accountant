package ru.gretchen.accountant.service;

import ru.gretchen.accountant.exception.TaskNotGetByDateException;
import ru.gretchen.accountant.exception.TaskNotSaveException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskNotSaveException(e.getMessage());
        }
    }

    public List<Task> getByDateNow() {
        try {
            return taskRepository.getByDate();
        } catch (Exception e) {
            throw new TaskNotGetByDateException(e.getMessage());
        }

    }
}
