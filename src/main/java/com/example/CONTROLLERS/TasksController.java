package com.example.CONTROLLERS;

import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
    @Autowired
    private TasksRepository tasksRepository;

    // @GetMapping("/tasks")
    @GetMapping("/2")
    public String tasksList(Model model){
        Iterable<Tasks> tasks = tasksRepository.findAll();
        model.addAttribute("listOfTasks", tasks);
        return "index";
    }

}
