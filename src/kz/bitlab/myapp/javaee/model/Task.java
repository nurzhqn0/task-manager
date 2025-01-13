package kz.bitlab.myapp.javaee.model;

public class Task {
    Long id;
    String name;
    String description;
    String deadlineDate;
    boolean completed;

    public Task() {
        this.id = (long) -1;
        this.name = "undefined";
        this.description = "undefined";
        this.deadlineDate = "undefined";
        this.completed = false;
    }

    public Task(Long id, String name, String description, String deadlineDate, Boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.completed = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
