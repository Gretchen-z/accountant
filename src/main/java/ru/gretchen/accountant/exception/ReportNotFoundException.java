package ru.gretchen.accountant.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message) {
        super("Отчёт на сегодня ещё не создан" + message);
    }
}
