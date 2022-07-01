package ru.gretchen.accountant.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUsernameHandler extends DefaultHandler {
    private String username = null;

    public String getUsername() {
        return username;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("username")) {
            username = attributes.getValue("username");
        }
    }
}
