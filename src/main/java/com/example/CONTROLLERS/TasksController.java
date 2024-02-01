package com.example.CONTROLLERS;

import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TasksController {
    @Autowired
    private TasksRepository tasksRepository;

    // @GetMapping("/tasks")
    @GetMapping
    public String tasksList(Model model){
        Iterable<Tasks> tasks = tasksRepository.findAll();
        model.addAttribute("listOfTasks", tasks);
        return "index";
    }

    @PostMapping("/task/create")
    public Object  addTasks(@RequestParam String task, @RequestParam String status, Model model)
        {
            Tasks tasks = new Tasks(status, task);
            tasksRepository.save(tasks);
        return "redirect:/";
         }



}
