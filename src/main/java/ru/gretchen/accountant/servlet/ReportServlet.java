package ru.gretchen.accountant.servlet;

import ru.gretchen.accountant.mapper.ReportMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reports/*")
public class ReportServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportMapper instance = ReportMapper.INSTANCE;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
