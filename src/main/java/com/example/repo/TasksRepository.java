package com.example.repo;

import com.example.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
