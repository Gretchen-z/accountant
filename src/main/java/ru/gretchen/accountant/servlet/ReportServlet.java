package ru.gretchen.accountant.servlet;

import ru.gretchen.accountant.mapper.ReportMapper;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.dto.ReportDTO;
import ru.gretchen.accountant.repository.ReportRepository;
import ru.gretchen.accountant.repository.TaskRepository;
import ru.gretchen.accountant.service.ReportService;
import ru.gretchen.accountant.service.TaskService;

import javax.mail.internet.ContentType;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private ReportService reportService;

    @Override
    public void init() throws ServletException {
        reportService = new ReportService(new ReportRepository(), new TaskService(new TaskRepository()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportMapper mapper = ReportMapper.INSTANCE;
        Report report = reportService.create();

        List<String> chatIds = report.getTasks().stream()
                .map(Task::getChatId)
                .distinct()
                .collect(Collectors.toList());

        // chatIds -> List<User>

        ReportDTO reportDTO = mapper.fromEntity(report, null);
        sendAsJson(resp, reportDTO);
    }

    private void sendAsJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");

        //TODO reportDto -> JSON String by Jackson
        String responseJson = "";

        resp.getWriter().write(responseJson);
        resp.getWriter().flush();
    }
}
