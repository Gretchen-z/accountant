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

/**
 * Сервис для парсинга xml в entity
 */
public class ParserService {
    private final TaskService taskService;

    public ParserService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     *
     * @param inputStream
     * @return List<User>
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * Метод для парсинга xml в List<User>
     */
    public List<User> xmlParseUser(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        List<User> users = new ArrayList<>();
        users.add(XmlSAXParser.xmlParseUser(inputStream));
        return users;
    }

    /**
     *
     * @param inputStream
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * Метод для парсинга xml в Task
     */
    public void xmlParseAndSaveTask(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        Task task = XmlSAXParser.xmlParseTask(inputStream);
        taskService.create(task);
    }
}
