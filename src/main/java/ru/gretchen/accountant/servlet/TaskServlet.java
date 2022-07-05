package ru.gretchen.accountant.servlet;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.xml.sax.SAXException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.ParserService;
import ru.gretchen.accountant.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TaskServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
       taskService = new TaskService(new TaskRepository());
    }

    /**
     *
     * @param req
     * @param resp
     * Метод принимает от сервиса-роутера информацию о Task и сохраняет в БД
     */
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        ServletInputStream inputStream = req.getInputStream();
        Gson gson = new Gson();
        String body = new String(inputStream.readAllBytes(), UTF_8);
        Task task = gson.fromJson(body, Task.class);
        task.setDate(LocalDate.now());
        taskService.create(task);

        System.out.println(body);
    }
}
