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

    public List<User> xmlParseUser(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        List<User> users = new ArrayList<>();
        users.add(XmlSAXParser.xmlParseUser(inputStream));
        return users;
    }

    public void xmlParseAndSaveTask(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        Task task = XmlSAXParser.xmlParseTask(inputStream);
        taskService.create(task);
    }

    //TODO реализовать через запрос к сервису-команде
    public InputStream requestUserByChatId(List<String> chatIds) {
        //1. Обернуть chatId в xml
        //2. Отправить xml
        //3. Получить в ответ xml
        //4. Вернуть поток вызывающему методу

        return null;
    }
}
