package kz.bitlab.myapp.javaee.db;

import kz.bitlab.myapp.javaee.model.Task;

import java.util.ArrayList;

public class DBManager {
    public static ArrayList<Task> allTasks = new ArrayList<>();

    static {
        allTasks.add(new Task(1L, "Nurzhan", "bala", "2024-11-25", false));
        allTasks.add(new Task(2L, "Ai", "bala", "2024-11-30", false));
        allTasks.add(new Task(3L, "n", "bala", "2024-12-29", true));

    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static void setAllTasks(ArrayList<Task> tasks) {
        DBManager.allTasks = tasks;
    }

    public static void addTask(String name, String description, String deadlineDate) {
        Long newTaskId = (allTasks.isEmpty()) ? 1L : allTasks.get(allTasks.size() - 1).getId() + 1;

        allTasks.add(new Task(newTaskId, name, description, deadlineDate, false));
        System.out.println("Task added: ID=" + newTaskId + ", Name=" + name + ", Description=" + description + ", Deadline=" + deadlineDate);
    }

    public static Task getTaskById(Long id){
        for(Task task : allTasks){
            if(task.getId().equals(id)){
                return task;
            }
        }

        return null;
    }

    public static boolean deleteTask(Long id){
        for(Task task : allTasks){
            if(task.getId().equals(id)){
                allTasks.remove(task);
                return true;
            }
        }

        return false;
    }
}
