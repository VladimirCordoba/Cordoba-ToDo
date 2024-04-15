package com.example.models;

import com.example.Priority;
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
   private Priority priority;

   private Long priorityNew;

    public Long getPriorityNew() {
        return priorityNew;
    }

    public void setPriorityNew(Long priorityNew) {
        this.priorityNew = priorityNew;
    }

    public Tasks( Status status, String task, Priority priority, Long priorityNew) {
      //  this.id = id;
        this.status = status;
        this.task = task;
        this.priority = priority;
        this.priorityNew = priorityNew;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Tasks(Long id, Status status, String task, Priority priority) {
        this.id = id;
        this.status = status;
        this.task = task;
        this.priority = priority;
    }

    public Tasks(Status status, String task, Priority priority) {
        this.status = status;
        this.task = task;
        this.priority = priority;
    }

    public Tasks(Long id, Priority priority) {
        this.id = id;
        this.priority = priority;
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
