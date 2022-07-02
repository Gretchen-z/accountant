package ru.gretchen.accountant.parser;

import org.xml.sax.SAXException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class XmlSAXParser {

    public static User xmlParseUser(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlUserHandler userHandler = new XmlUserHandler();
        parser.parse(inputStream, userHandler);
        return userHandler.getUser();
    }

    public static Task xmlParseTask(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlTaskHandler taskHandler = new XmlTaskHandler();
        parser.parse(inputStream, taskHandler);
        return taskHandler.getTask();
    }

    public static String xmlParseUsername(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlUsernameHandler usernameHandler = new XmlUsernameHandler();
        parser.parse(inputStream, usernameHandler);
        return usernameHandler.getChatId();
    }
}
