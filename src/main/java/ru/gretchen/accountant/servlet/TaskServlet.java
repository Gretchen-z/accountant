package ru.gretchen.accountant.servlet;

import ru.gretchen.accountant.service.ParserService;

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
//       parserService.requestUserByUsername()
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
//        parser.xmlParse(inputStream);

        
    }
}
