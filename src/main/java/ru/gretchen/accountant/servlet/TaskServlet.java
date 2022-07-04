package ru.gretchen.accountant.servlet;

import org.xml.sax.SAXException;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.ParserService;
import ru.gretchen.accountant.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TaskServlet extends HttpServlet {

    private ParserService parserService;

    @Override
    public void init() throws ServletException {
       parserService = new ParserService(new TaskService(new TaskRepository()));
    }

    /**
     *
     * @param req
     * @param resp
     * Метод принимает от сервиса-роутера информацию о Task и сохраняет в БД
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ServletInputStream inputStream = null;
        try {
            inputStream = req.getInputStream();
        } catch (IOException e) {
        }
        try {
            parserService.xmlParseAndSaveTask(inputStream);
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }
    }
}
