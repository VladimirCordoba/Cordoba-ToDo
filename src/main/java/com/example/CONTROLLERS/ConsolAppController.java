package com.example.CONTROLLERS;


import com.example.ArrangedList;
import com.example.Priority;
import com.example.Status;
import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController  // поменял @Controller на @RestController поскольку он возвращает JSON
// @Controller
public  class ConsolAppController {

    @Autowired
    private TasksRepository tasksRepository;


   @GetMapping ("/consol/task/list")  // Тут получаем список всех тасеов из Базы для консольног приложения
    // @RequestMapping("/consol/task/list")
    public ArrayList consolAllTasksList1(Model model){
        //  ArrangedList.arrange(tasksRepository);

      //  System.out.println(ArrangedList.arrange(tasksRepository).toString());
        return (ArrangedList.arrange(tasksRepository));
    }
    @PostMapping("/consol/task/create")
    public  void addTasks(@RequestParam String task, Model model) {
        Tasks tasks = new Tasks(Status.OPEN, task, Priority.LOW);
        tasksRepository.save(tasks);

    }
    @PostMapping("/consol/task/close")
    public  void closeTasks(@RequestParam String id1, Model model) {
     // String sid = id1;
     int  intid = Integer.parseInt(id1); //преобразовываем строку в число.
        Long id =(long)intid;
        if(!tasksRepository.existsById(id)){
           System.out.println("id does not exist");
        }
        Tasks task = tasksRepository.findById(id).orElseThrow();
        task.setStatus(Status.CLOSED);
        tasksRepository.save(task);

    }



}
