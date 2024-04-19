package com.example;

import com.example.models.Task;
import com.example.repo.TasksRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public  class ArrangedList {

    public static ArrayList<Task> arrange(TasksRepository tasksRepository) {

        ArrayList<Task> taskList = new ArrayList<>();
        tasksRepository.findAll().forEach(taskList::add);
        Comparator<Task> compareByPriority = Comparator.comparing(Task::getPriorityNew );
                ArrayList<Task> sortedTasks = taskList.stream().sorted(compareByPriority.reversed()).collect(Collectors
                .toCollection(ArrayList::new));
      return  sortedTasks;
    }


    public static Long  maxPriorityNewMethods(TasksRepository tasksRepository) {

        ArrayList<Task> list = arrange(tasksRepository);

        return list.get(0).getPriorityNew();

    }
}
