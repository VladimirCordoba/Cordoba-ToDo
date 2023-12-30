package com.example;

public class TaskMessage {
    private Integer id;
    private String tMessage;
    private String status;

    public TaskMessage(String status) {
        this.status = status;
    }

    public TaskMessage(Integer id, String tMessage, String status) {
        this.id = id;
        this.tMessage = tMessage;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettMessage() {
        return tMessage;
    }

    public void settMessage(String tMessage) {
        this.tMessage = tMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
