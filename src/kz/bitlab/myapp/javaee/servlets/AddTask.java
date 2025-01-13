package kz.bitlab.myapp.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.myapp.javaee.db.DBManager;
import kz.bitlab.myapp.javaee.model.Task;

import java.io.IOException;

@WebServlet(value = "/add-task")
public class AddTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method invoked");
        String taskName = req.getParameter("taskName");
        String taskDescription = req.getParameter("taskDescription");
        String dueDate = req.getParameter("dueDate");

        DBManager.addTask(taskName, taskDescription, dueDate);

        resp.sendRedirect("/");
    }
}
