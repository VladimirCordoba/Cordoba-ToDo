package com.example.CONTROLLERS;

import com.example.Status;
import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TasksController {
    @Autowired
    private TasksRepository tasksRepository;


    @GetMapping
    public String tasksList(Model model) {

        return "index";
    }

    @PostMapping("/task/create")
    public Object addTasks(@RequestParam String task, Model model) {
      Tasks  tasks = new Tasks(Status.OPEN, task);
    //  tasks.setStatus(Status.OPEN);
        tasksRepository.save(tasks);

        Iterable<Tasks> tasks1 = tasksRepository.findAll();
        model.addAttribute("listOfTasks", tasks1);

        /*return "redirect:/result";*/
        return "result";
    }

    @PostMapping("/task/delate")
    public String taskDelate(@RequestParam Long id, Model model) {
        tasksRepository.deleteById(id);
        // model.addAttribute("listOfTasks", tasks);
        Iterable<Tasks> tasks1 = tasksRepository.findAll();
        model.addAttribute("listOfTasks", tasks1);
        return "result";
    }

    @PostMapping("/task/statusToX")
    public String taskStatusToX(@RequestParam Long id, Model model) {
        if(!tasksRepository.existsById(id)){
            return"result";
        }
        Tasks task = tasksRepository.findById(id).orElseThrow();
        task.setStatus(Status.CLOSED);
         tasksRepository.save(task);
        // model.addAttribute("listOfTasks", tasks);
        Iterable<Tasks> tasks1 = tasksRepository.findAll();
        model.addAttribute("listOfTasks", tasks1);
        return "result";

    }

    @PostMapping("/task/list")
    public Object tasksAllTasksList1(Model model) {


        Iterable<Tasks> tasks1 = tasksRepository.findAll();
        model.addAttribute("listOfTasks", tasks1);

        /*return "redirect:/result";*/
        return "result";
    }

    @GetMapping("/task/edit")
    public Object editTasks(@RequestParam Long id, Model model) {
        if(!tasksRepository.existsById(id)){
            return"result";
        }
       Tasks tasks1 = tasksRepository.findById(id).orElseThrow();
        model.addAttribute("listOfTasks", tasks1);
      // tasks1.setStatus("X");
     //  tasks1.setTask(task);
      // tasksRepository.save(tasks1);

        //return "redirect:/taskEdit";
        return "taskEdit";
    }
    @PostMapping("/task/{id}/update")
    public Object updateTasks(@PathVariable (value="id") long id, @RequestParam String task, Status status, Model model) {
        if(!tasksRepository.existsById(id)){
            return"result";
        }
        Tasks tasks1 = tasksRepository.findById(id).orElseThrow();
        model.addAttribute("listOfTasks", tasks1);
        tasks1.setStatus(status);
        tasks1.setTask(task);
        tasksRepository.save(tasks1);

       // return "redirect:/taskEdit";
        return "taskEdit";
    }


}