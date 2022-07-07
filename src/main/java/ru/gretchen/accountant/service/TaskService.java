package ru.gretchen.accountant.service;

import ru.gretchen.accountant.exception.TaskNotGetByDateException;
import ru.gretchen.accountant.exception.TaskNotSaveException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.repository.TaskRepository;

import java.util.List;

/**
 * Сервис для работы с Task repository
 */
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     *
     * @param task
     * @return Task
     * Метод для сохранения Task в БД
     */
    public Task create(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskNotSaveException(e.getMessage());
        }
    }

    /**
     *
     * @return List<Task>
     * Метод для получения из БД всех Task по определённой дате
     */
    public List<Task> getByDateNow() {
        try {
            return taskRepository.getByDate();
        } catch (Exception e) {
            throw new TaskNotGetByDateException(e.getMessage());
        }

    }
}
