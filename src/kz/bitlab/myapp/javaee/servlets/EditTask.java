package kz.bitlab.myapp.javaee.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.myapp.javaee.db.DBManager;
import kz.bitlab.myapp.javaee.model.Task;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(value = "/task/*")
public class EditTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String pathInfo = req.getPathInfo(); // Extracts the path after /task/
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h3>Task ID is required</h3>");
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h3>Invalid URL format</h3>");
            return;
        }

        try {
            Long taskId = Long.parseLong(pathParts[1]);

            Optional<Task> taskOpt = DBManager.allTasks.stream()
                    .filter(task -> task.getId().equals(taskId))
                    .findFirst();

            if (taskOpt.isPresent()) {
                req.setAttribute("task", taskOpt.get());
                req.getRequestDispatcher("/edit-task.jsp").forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<h3>Task not found</h3>");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h3>Invalid Task ID format</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if (method != null && method.equalsIgnoreCase("DELETE")) {
            doDelete(req, resp);
            return;
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h3>Task ID is required for editing</h3>");
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h3>Invalid URL format</h3>");
            return;
        }

        try {
            Long taskId = Long.parseLong(pathParts[1]);

            Optional<Task> taskOpt = DBManager.allTasks.stream()
                    .filter(task -> task.getId().equals(taskId))
                    .findFirst();

            if (taskOpt.isPresent()) {
                Task task = taskOpt.get();

                String name = req.getParameter("name");
                String description = req.getParameter("description");
                String dueData = req.getParameter("dueDate");
                String completed = req.getParameter("completed");

                if (name != null && !name.isEmpty()) task.setName(name);
                if (description != null && !description.isEmpty()) task.setDescription(description);
                if (dueData != null && !dueData.isEmpty()) task.setDeadlineDate(dueData);
                if (completed != null && !completed.isEmpty()) task.setCompleted(Boolean.parseBoolean(completed));


                resp.sendRedirect("/");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<h3>Task not found</h3>");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h3>Invalid Task ID format</h3>");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Task ID is required for deletion.");
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid URL format. Task ID is missing.");
            return;
        }

        try {
            Long taskId = Long.parseLong(pathParts[1]);

            boolean removed = DBManager.allTasks.removeIf(task -> task.getId().equals(taskId));

            if (removed) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Task successfully deleted.");
                response.sendRedirect("/");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Task not found.");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid Task ID format.");
        }
    }

}
