package ru.gretchen.accountant.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.model.enumeration.Team;

public class XmlUserHandler extends DefaultHandler {
    private final User user = new User();

    public User getUser() {
        return user;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("user")) {
            user.setUsername(attributes.getValue("username"));
            user.setName(attributes.getValue("name"));
            user.setLastName(attributes.getValue("last_name"));
            user.setTeam(Team.valueOf(attributes.getValue("team")));
        }
    }
}
