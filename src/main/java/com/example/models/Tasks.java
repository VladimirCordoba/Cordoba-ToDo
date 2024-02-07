package com.example.models;

import com.example.Status;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import jakarta.persistence.*;

import static com.example.Status.OPEN;

@Entity
public class Tasks {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

   private  Status status;


   private String task;

    public Tasks(Long id, Status status, String task) {
        this.id = id;
        this.status = status;
        this.task = task;
    }

    public Tasks(Status status, String task) {
        this.status = status;
        this.task = task;
    }

    public Tasks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
