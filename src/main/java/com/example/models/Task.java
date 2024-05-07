package com.example.models;

import com.example.Priority;
import com.example.Status;
import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

   private  Status status;

   private String task;
   private Priority priority;

   private long priorityNew;

    public Task(long id, Status status, String tasktext, Priority priority, long priorityNew) {
        this.id = id;
        this.status = status;
        this.task = tasktext;
        this.priority = priority;
        this.priorityNew = priorityNew;
    }

    public long getPriorityNew() {
        return priorityNew;
    }

    public void setPriorityNew(long priorityNew) {

        this.priorityNew = priorityNew;
    }

    public Task(Status status, String task, Priority priority, long priorityNew) {
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

    public Task(Long id, Status status, String task, Priority priority) {
        this.id = id;
        this.status = status;
        this.task = task;
        this.priority = priority;
    }

    public Task(Status status, String task, Priority priority) {
        this.status = status;
        this.task = task;
        this.priority = priority;
    }

    public Task(Long id, Priority priority) {
        this.id = id;
        this.priority = priority;
    }


    public Task() {
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
