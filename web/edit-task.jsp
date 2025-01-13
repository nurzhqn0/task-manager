<%@ page import="kz.bitlab.myapp.javaee.model.Task" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Task</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<header class="navbar navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand text-white" href="/">Task Manager</a>
    </div>
</header>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">

            <%
                Task task = (Task) request.getAttribute("task");
                if (task == null) {
            %>

            <div class="alert alert-danger" role="alert">
                Task not found.
            </div>
            <%
            } else {
            %>
            <form action="/task/<%= task.getId() %>" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Название задания</label>
                    <input
                            type="text"
                            class="form-control"
                            id="name"
                            name="taskName"
                            value="<%= task.getName() %>"
                            required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Описание задания</label>
                    <textarea
                            class="form-control"
                            id="description"
                            name="taskDescription"
                            rows="3"
                            required><%= task.getDescription() %></textarea>
                </div>

                <div class="mb-3">
                    <label for="dueDate" class="form-label">Срок выполнения</label>
                    <input
                            type="date"
                            class="form-control"
                            id="dueDate"
                            name="dueDate"
                            value="<%= task.getDeadlineDate() %>"
                            required>
                </div>

                <div class="mb-3">
                    <label for="completed" class="form-label">Выполнено</label>
                    <select class="form-control" id="completed" name="completed" required>
                        <option value="true" <%= task.isCompleted() ? "selected" : "" %>>Да</option>
                        <option value="false" <%= !task.isCompleted() ? "selected" : "" %>>Нет</option>
                    </select>
                </div>

                <div class="d-flex justify-content-start gap-3">
                    <form action="/task/<%= task.getId() %>" method="post">
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </form>

                    <a href="/" class="btn btn-secondary">Back to Tasks</a>
                    <form action="/task/<%= task.getId() %>" method="post" onsubmit="return confirm('Are you sure you want to delete this task?');">
                        <input type="hidden" name="_method" value="DELETE">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </form>

            <%
                }
            %>

        </div>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
