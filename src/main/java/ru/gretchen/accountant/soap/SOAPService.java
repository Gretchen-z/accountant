package ru.gretchen.accountant.soap;

import ru.gretchen.accountant.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с SOAP-сервисами
 */
public class SOAPService {
    /**
     *
     * @param chatIds
     * @return List<User>
     * Метод запрашивает у сервиса-команды данные по User у сервиса-команды
     */
    public List<User> requestUserByChatId(List<String> chatIds) {
        CommandImplService command = new CommandImplService();
        Command commandI = command.getCommandImplPort();
        SetOfUser users = commandI.getAllUsersAndGroups();
        List<ru.gretchen.accountant.soap.User> userItems = users.getItem();
        List<User> userList = new ArrayList<>();

        for (ru.gretchen.accountant.soap.User user : userItems) {
            userList.add(new User(user.getChatId(), user.getFullName(), user.getGroup()));
        }

        userList = userList.stream()
                .filter(user -> chatIds.contains(user.getChatId()))
                .collect(Collectors.toList());

        return userList;
    }
}
