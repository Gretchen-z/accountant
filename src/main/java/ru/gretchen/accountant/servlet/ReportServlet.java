package ru.gretchen.accountant.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.gretchen.accountant.mapper.ReportMapper;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.model.dto.ReportDTO;
import ru.gretchen.accountant.repository.ReportRepository;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.ParserService;
import ru.gretchen.accountant.service.ReportService;
import ru.gretchen.accountant.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private ReportService reportService;
    private ParserService parserService;

    @Override
    public void init() throws ServletException {
        reportService = new ReportService(new ReportRepository(),
                new TaskService(new TaskRepository()));
        parserService = new ParserService(new TaskService(new TaskRepository()));
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * Метод отправляет в ответ на запрос сервиса-отправителя
     * сформированный Report в формате JSON.
     * В процессе формирования отчёта обращается к сервису-команде
     * для получения информации о User, нужной для Report.
     */
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportMapper mapper = ReportMapper.INSTANCE;
        Report report = reportService.create();

        List<String> chatIds = report.getTasks().stream()
                .map(Task::getChatId)
                .distinct()
                .collect(Collectors.toList());

        //TODO реализовать обращение к сервису-команде
        InputStream usersInputStream = parserService.requestUserByChatId(chatIds);
        List<User> users = parserService.xmlParseUser(usersInputStream);

        ReportDTO reportDTO = mapper.fromEntity(report, users);

        sendAsJson(resp, reportDTO);
    }

    private void sendAsJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");

        //TODO протестировать
        ObjectMapper mapper = new ObjectMapper();
        String responseJson = mapper.writeValueAsString(obj);

        resp.getWriter().write(responseJson);
        resp.getWriter().flush();
    }
}
