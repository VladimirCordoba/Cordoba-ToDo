package com.example;

import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;



public  class ArrangedList {

    ArrayList<Tasks> tasksList = new ArrayList<>();
    public ArrayList arrange(TasksRepository tasksRepository) {
      tasksRepository.findAll().forEach(tasksList::add);
        Comparator<Tasks> compareByPriority = Comparator.comparing(Tasks::getPriority );
        ArrayList<Tasks> sortedTasks =tasksList.stream().sorted(compareByPriority).collect(Collectors
                .toCollection(ArrayList::new));
      return  sortedTasks;
    }
}