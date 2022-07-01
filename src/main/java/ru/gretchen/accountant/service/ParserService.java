package ru.gretchen.accountant.service;

import org.xml.sax.SAXException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.parser.XmlSAXParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class ParserService {
    private final TaskService taskService;
    private final UserService userService;

    public ParserService(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    User user = new User();
    Task task = new Task();

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }

    public void xmlParseAndSaveUser(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        String username = XmlSAXParser.xmlParseUsername(inputStream);
        InputStream userInputStream = requestUserByUsername(username);
        user = XmlSAXParser.xmlParseUser(userInputStream);
        userService.updateOrCreate(user);
    }

    public void xmlParseAndSaveTask(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        task = XmlSAXParser.xmlParseTask(inputStream);
        taskService.create(task, user);
    }

    //TODO реализовать через запрос к сервису-команде
    public InputStream requestUserByUsername(String username) {
        return null;
    }
}
