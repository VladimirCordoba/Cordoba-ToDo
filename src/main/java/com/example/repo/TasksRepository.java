package com.example.repo;

import com.example.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TasksRepository extends JpaRepository<Task, Long> {
}
