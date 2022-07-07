package ru.gretchen.accountant.servlet;

import com.google.gson.Gson;
import ru.gretchen.accountant.exception.TaskParseException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Сервлет для работы с Task
 * endpoint /accountant/tasks
 */
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ServletInputStream inputStream = req.getInputStream();
            Gson gson = new Gson();
            String body = new String(inputStream.readAllBytes(), UTF_8);
            Task task = gson.fromJson(body, Task.class);
            task.setDate(LocalDate.now());
            taskService.create(task);
        } catch (IOException e) {
            throw new TaskParseException(e.getMessage());
        }
    }
}
