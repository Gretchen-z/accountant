package ru.gretchen.accountant.servlet;

import com.google.gson.Gson;
import ru.gretchen.accountant.mapper.ReportMapper;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.model.dto.ReportDTO;
import ru.gretchen.accountant.repository.ReportRepository;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.ReportService;
import ru.gretchen.accountant.service.TaskService;
import ru.gretchen.accountant.soap.SOAPService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReportServlet extends HttpServlet {

    private ReportService reportService;
    private SOAPService soapService;

    @Override
    public void init() throws ServletException {
        reportService = new ReportService(new ReportRepository(),
                new TaskService(new TaskRepository()));
        soapService = new SOAPService();
    }

    /**
     *
     * @param req
     * @param resp
     * Метод отправляет в ответ на запрос сервиса-отправителя
     * сформированный Report в формате JSON.
     * В процессе формирования отчёта обращается к сервису-команде
     * для получения информации о User, нужной для Report.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        ReportMapper mapper = ReportMapper.INSTANCE;
        Report report = reportService.create();

        List<String> chatIds = report.getTasks().stream()
                .map(Task::getChatId)
                .distinct()
                .collect(Collectors.toList());


//        soapService.requestSendListChatId(chatIds);
        List<User> users = soapService.requestUserByChatId(chatIds);

        ReportDTO reportDTO = mapper.fromEntity(report, users);

        try {
            sendAsJson(resp, reportDTO);
            resp.setStatus(200);
        } catch (IOException e) {
        }
    }

    private void sendAsJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json; charset=utf-8");

        Gson gson = new Gson();
        String responseJson = gson.toJson(obj);

        resp.getWriter().write(responseJson);
        resp.getWriter().flush();
    }
}
