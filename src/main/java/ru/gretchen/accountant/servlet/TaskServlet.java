package ru.gretchen.accountant.servlet;

import lombok.SneakyThrows;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.ParserService;
import ru.gretchen.accountant.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    private ParserService parserService;

    @Override
    public void init() throws ServletException {
       parserService = new ParserService(new TaskService(new TaskRepository()));
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        parserService.xmlParseUser(inputStream);
        parserService.xmlParseAndSaveTask(inputStream);
    }
}
