package ru.gretchen.accountant.exception;

public class ListChatIdsException extends RuntimeException{
    public ListChatIdsException(String message) {
        super("Не удалось записать и отправить список chatIds " + message);
    }
}
