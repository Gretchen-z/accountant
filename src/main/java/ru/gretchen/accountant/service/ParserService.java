package ru.gretchen.accountant.service;

import org.xml.sax.SAXException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.parser.XmlSAXParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserService {
    private final TaskService taskService;

    public ParserService(TaskService taskService) {
        this.taskService = taskService;
    }

    List<User> users = new ArrayList<>();
    Task task = new Task();

    public Task getTask() {
        return task;
    }

    public void xmlParseUser(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        String chatId = XmlSAXParser.xmlParseUsername(inputStream);
        InputStream userInputStream = requestUserByUsername(chatId);
        users.add(XmlSAXParser.xmlParseUser(userInputStream));
    }

    public void xmlParseAndSaveTask(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        task = XmlSAXParser.xmlParseTask(inputStream);
        taskService.create(task);
    }

    //TODO реализовать через запрос к сервису-команде
    public InputStream requestUserByUsername(String username) {
        return null;
    }
}
