package ru.gretchen.accountant.soap;

import ru.gretchen.accountant.model.User;

import java.util.List;

public class SOAPService {
    public static void setup() {
//        CommandImplService service = new CommandImplService();
    }

    public List<User> requestUserByChatId(List<String> chatIds) {
        //1. Обернуть chatId в xml
        //2. Отправить xml
        //3. Получить в ответ xml
        //4. Вернуть поток вызывающему методу

//        CommandImplService service = new CommandImplService();

        return null;
    }

    public static void main(String[] args) {
        CommandImplService command = new CommandImplService();

        Command command1 = command.getCommandImplPort();

        SetOfUser users = command1.getAllUsersAndGroups();

        System.out.print(users.getItem().get(1).getFullName());
    }
}
