package ru.gretchen.accountant.soap;

import ru.gretchen.accountant.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<ru.gretchen.accountant.soap.User> userItems = users.getItem();
        List<User> userList = new ArrayList<>();

        for (ru.gretchen.accountant.soap.User user : userItems) {
            userList.add(new User(user.getChatId(), user.getFullName(), user.getGroup()));
        }

        userList.stream().filter(user -> chatIds.contains(user.getChatId())).collect(Collectors.toList());

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
        Set<String> chatIdsI = new HashSet<>(chatIds);
        serviceI.takeTrackedList(chatIdsI);
    }

    public static void main(String[] args) {
        List<String> chatIds = new ArrayList<>();
        chatIds.add("1234567");
        chatIds.add("3456783");
        chatIds.add("4563929");
        chatIds.add("7775757");
        SOAPService service = new SOAPService();
        service.requestSendListChatId(chatIds);
    }
}
