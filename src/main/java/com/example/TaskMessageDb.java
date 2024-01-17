package com.example;

public class TaskMessageDb {

    private Integer id;
    private String task;
    private String status;

    public TaskMessageDb(Integer id, String task, String status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
