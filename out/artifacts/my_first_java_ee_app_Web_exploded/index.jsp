<%@ page import="kz.bitlab.myapp.javaee.db.DBManager" %>
<%@ page import="kz.bitlab.myapp.javaee.model.Task" %>
<%@ page import="kz.bitlab.myapp.javaee.db.DBConnector" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Task Manager</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
</head>
<body>
<header class="navbar navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand text-white" href="/">Task Manager</a>
    </div>
</header>

<section class="container mx-5">
    <button class="btn btn-primary mt-5" data-bs-toggle="modal" data-bs-target="#addTaskModal">
        + Добавить задание
    </button>

    <div class="modal fade w-1/2" id="addTaskModal" tabindex="-1" aria-labelledby="addTaskModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addTaskModalLabel">Добавить задание</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <form method="post" action="/add-task">
                        <div class="mb-3">
                            <label for="taskName" class="form-label">Название задания</label>
                            <input type="text" class="form-control" id="taskName" name="taskName" required>
                        </div>

                        <div class="mb-3">
                            <label for="taskDescription" class="form-label">Описание задания</label>
                            <textarea class="form-control" id="taskDescription" name="taskDescription" rows="3" required></textarea>
                        </div>

                        <div class="mb-3">
                            <label for="dueDate" class="form-label">Срок выполнения</label>
                            <input type="date" class="form-control" id="dueDate" name="dueDate" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Сохранить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="mt-5">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Крайний срок</th>
                <th>Выполнено</th>
                <th>Детали</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Task task : DBConnector.getTasks()) {
            %>
            <tr >
                <td scope="row"><%= task.getId() %></td>
                <td><%= task.getName() %></td>
                <td><%= task.getDeadlineDate() %></td>
                <% String state = task.isCompleted() ? "Да" : "Нет"; %>
                <td><%= state %></td>
                <td><a href="/task/<%= task.getId() %>" class="btn btn-primary">Детали</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
