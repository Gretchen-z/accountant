package ru.gretchen.accountant.servlet;

import com.google.gson.Gson;
import ru.gretchen.accountant.exception.ListChatIdsException;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервлет для работы с User
 * endpoint /accountant/users
 */
public class UserServlet extends HttpServlet {
    TaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    /**
     *
     * @param req
     * @param resp
     * Метод отправляет сервису-нотификатору список chatId затрекавшихся сегодня User'ов
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Task> todayTasks = taskRepository.getByDate();
        List<String> chatIds = todayTasks.stream()
                .map(Task::getChatId)
                .distinct()
                .collect(Collectors.toList());

        try {
            sendAsJson(resp, chatIds);
            resp.setStatus(200);
        } catch (IOException e) {
            throw new ListChatIdsException(e.getMessage());
        }
    }

    /**
     *
     * @param resp
     * @param obj
     * @throws IOException
     * Метод для преобразования объекта в JSON
     */
    private void sendAsJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json; charset=utf-8");

        Gson gson = new Gson();
        String responseJson = gson.toJson(obj);

        resp.getWriter().write(responseJson);
        resp.getWriter().flush();
    }
}
