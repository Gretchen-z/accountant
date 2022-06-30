package ru.gretchen.accountant.parser;

import org.xml.sax.SAXException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.service.TaskService;
import ru.gretchen.accountant.service.UserService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlSAXParser {
    private final UserService userService;

    public XmlSAXParser(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    private final TaskService taskService;

    public void xmlParse(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlTaskHandler taskHandler = new XmlTaskHandler();
        parser.parse(xmlFile, taskHandler);
        Task task = taskHandler.getTask();

        XmlUserHandler userHandler = new XmlUserHandler();
        parser.parse(xmlFile, userHandler);
        User user = userHandler.getUser();

        userService.getOrCreate(user);
        taskService.create(task, user);
    }
}