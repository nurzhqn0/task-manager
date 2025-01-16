package kz.bitlab.myapp.javaee.db;

import kz.bitlab.myapp.javaee.model.Task;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC Driver loaded successfully.");

            // Establish the connection
            String url = "jdbc:postgresql://localhost:5432/database-tasks";
            String username = "postgres";
            String password = "2925";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established successfully.");

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver class not found. Make sure the JDBC driver is in the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to establish a database connection. Check the URL, username, or password.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("Database connection is not initialized. Check your DB configuration.");
        }
        return connection;
    }

    public static ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM table_tasks");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String deadlineDate = resultSet.getString("deadline_date");
                boolean completed = resultSet.getBoolean("completed");

                tasks.add(new Task(id, name, description, deadlineDate, completed));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public static void addTask(Task task){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO table_tasks (name, description, deadline_date, completed) VALUES (?, ?, ?)"
            );

            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, Date.valueOf(task.getDeadlineDate()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
