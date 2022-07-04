package ru.gretchen.accountant.soap;

import ru.gretchen.accountant.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с SOAP-сервисами
 */
public class SOAPService {

    /**
     *
     * @param chatIds
     * @return
     * Метод запрашивает у сервиса-команды данные по User у сервиса-команды
     */
    public List<User> requestUserByChatId(List<String> chatIds) {
        CommandImplService command = new CommandImplService();
        Command command1 = command.getCommandImplPort();
        SetOfUser users = command1.getAllUsersAndGroups();
        List<ru.gretchen.accountant.soap.User> userItems = (List<ru.gretchen.accountant.soap.User>) users.getItem();
        List<User> userList = new ArrayList<>();

        for (ru.gretchen.accountant.soap.User user : userItems) {
            userList.add(new User(user.getChatId(), user.getFullName(), user.getGroup()));
        }
        return userList;
    }

    /**
     *
     * @param chatIds
     * Метод отправляет сервису-нотификатору список chatId затрекавшихся сегодня User'ов
     */
    //TODO подключить сервис и реализовать
    public void requestSendListChatId(List<String> chatIds) {

    }
}
