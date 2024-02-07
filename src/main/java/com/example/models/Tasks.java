package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tasks {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String status; String task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Tasks() {
    }

    public Tasks(String status, String task) {
        this.status = status;
        this.task = task;
    }

    public Tasks(Long id, String status, String task) {
        this.id = id;
        this.status = status;
        this.task = task;
    }
}
