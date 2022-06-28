package ru.gretchen.accountant.exception;

public class GetSessionException extends RuntimeException{
    public GetSessionException(String message) {
        super("Не удалось получить фабрику сессий: " + message);
    }
}
