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
        Command commandI = command.getCommandImplPort();
        SetOfUser users = commandI.getAllUsersAndGroups();
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
    public void requestSendListChatId(List<String> chatIds) {
        TrackedChatIdServiceImplService service = new TrackedChatIdServiceImplService();
        TrackedChatIdService serviceI = service.getTrackedChatIdServiceImplPort();
        serviceI.takeTrackedList(chatIds.toString());
    }
}
