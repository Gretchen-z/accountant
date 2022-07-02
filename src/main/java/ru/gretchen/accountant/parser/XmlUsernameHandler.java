package ru.gretchen.accountant.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUsernameHandler extends DefaultHandler {
    private String chatId = null;

    public String getChatId() {
        return chatId;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("chatId")) {
            chatId = attributes.getValue("chatId");
        }
    }
}
