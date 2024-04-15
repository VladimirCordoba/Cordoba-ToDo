package com.example;

import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public  class ArrangedList {

    public static ArrayList<Tasks> arrange(TasksRepository tasksRepository) {

        ArrayList<Tasks> tasksList = new ArrayList<>();
        tasksRepository.findAll().forEach(tasksList::add);
        Comparator<Tasks> compareByPriority = Comparator.comparing(Tasks::getPriorityNew );
                ArrayList<Tasks> sortedTasks =tasksList.stream().sorted(compareByPriority.reversed()).collect(Collectors
                .toCollection(ArrayList::new));
      return  sortedTasks;
    }


    public static Long  maxPriorityNewMethods(TasksRepository tasksRepository) {

        ArrayList<Tasks> list = arrange(tasksRepository);

        return list.get(0).getPriorityNew();

    }
}
