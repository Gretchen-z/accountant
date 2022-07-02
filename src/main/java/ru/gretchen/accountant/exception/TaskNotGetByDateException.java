package ru.gretchen.accountant.exception;

public class TaskNotGetByDateException extends RuntimeException{
    public TaskNotGetByDateException(String message) {
        super("Не удалось получить сегодняшние задачи " + message);
    }
}
