package ru.gretchen.accountant.exception;

public class TaskNotSaveException extends RuntimeException{
    public TaskNotSaveException(String message) {
        super("Не удалось создать новую задачу " + message);
    }
}
