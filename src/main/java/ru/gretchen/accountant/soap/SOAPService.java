package ru.gretchen.accountant.soap;

import ru.gretchen.accountant.model.User;

import java.util.ArrayList;
import java.util.List;

public class SOAPService {

    public List<User> requestUserByChatId(List<String> chatIds) {
        CommandImplService command = new CommandImplService();
        Command command1 = command.getCommandImplPort();
        SetOfUser users = command1.getAllUsersAndGroups();
        List<ru.gretchen.accountant.soap.User> userItems = users.getItem();
        List<User> userList = new ArrayList<>();

        for (ru.gretchen.accountant.soap.User user : userItems) {
            userList.add(new User(user.getChatId(), user.getFullName(), user.getGroup()));
        }
        return userList;
    }

    //TODO реализовать
    public void requestSendListChatId(List<String> chatIds) {

    }

    public static void main(String[] args) {
        CommandImplService command = new CommandImplService();
        Command command1 = command.getCommandImplPort();
        SetOfUser users = command1.getAllUsersAndGroups();
        System.out.print(users.getItem().get(1).getFullName());
    }
}
