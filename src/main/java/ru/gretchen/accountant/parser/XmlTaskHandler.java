package ru.gretchen.accountant.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.gretchen.accountant.model.Task;

import java.time.LocalDate;

public class XmlTaskHandler extends DefaultHandler {
    private final Task task = new Task();

    public Task getTask() {
        return task;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("task")) {
            task.setChatId(attributes.getValue("chatId"));
            task.setDescription(attributes.getValue("description"));
            task.setTimeInMinutes(Integer.parseInt(attributes.getValue("timeInMinutes")));
            task.setDate(LocalDate.now());
        }
    }
}
