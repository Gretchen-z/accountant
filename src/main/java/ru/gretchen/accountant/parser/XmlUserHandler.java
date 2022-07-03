package ru.gretchen.accountant.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.gretchen.accountant.model.User;

public class XmlUserHandler extends DefaultHandler {
    private final User user = new User();

    public User getUser() {
        return user;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("user")) {
            user.setChatId(attributes.getValue("chatId"));
            user.setFullName(attributes.getValue("name"));
            user.setGroup(attributes.getValue("team"));
        }
    }
}
