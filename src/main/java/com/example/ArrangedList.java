package com.example;

import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;



public  class ArrangedList {

    public static ArrayList arrange(TasksRepository tasksRepository) {
     //   ArrayList<Tasks> tasksListTmp = new ArrayList<>();
        ArrayList<Tasks> tasksList = new ArrayList<>();

    //  tasksRepository.findAll().forEach(tasksListTmp::add);

   ///   Tasks lastEl = tasksListTmp.get((int)tasksRepository.count()-2);  //----
   //    Long  id = lastEl.getId(); //-----
  //      lastEl.setPriority(Priority.MEDIUM); //----
   //     tasksRepository.save(lastEl); //-----
        tasksRepository.findAll().forEach(tasksList::add);




        Comparator<Tasks> compareByPriority = Comparator.comparing(Tasks::getPriority );
      //  ArrayList<Tasks> sortedTasks =tasksList.stream().sorted(compareByPriority).collect(Collectors
        ArrayList<Tasks> sortedTasks =tasksList.stream().sorted(compareByPriority.thenComparingLong(Tasks::getId)).collect(Collectors
        //ArrayList<Tasks> sortedTasks =tasksList.stream().sorted(compareByPriority.reversed()).collect(Collectors
                .toCollection(ArrayList::new));
      return  sortedTasks;
    }
}
