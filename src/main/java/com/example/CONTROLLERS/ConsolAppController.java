package com.example.CONTROLLERS;


import com.example.ArrangedList;
import com.example.Priority;
import com.example.Status;
import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController  // поменял @Controller на @RestController поскольку он возвращает JSON
// @Controller
public class ConsolAppController {

    @Autowired
    private TasksRepository tasksRepository;


    @GetMapping("/consol/task/list")  // Тут получаем список всех тасеов из Базы для консольног приложения
    // @RequestMapping("/consol/task/list")
    public ArrayList consolAllTasksList1(Model model) {
        //  ArrangedList.arrange(tasksRepository);

        //  System.out.println(ArrangedList.arrange(tasksRepository).toString());
        return (ArrangedList.arrange(tasksRepository));
    }

    @PostMapping("/consol/task/create")
    public void addTasks(@RequestParam String task, Model model) {
        Tasks tasks = new Tasks(Status.OPEN, task, Priority.LOW);
        tasksRepository.save(tasks);

    }

    @PostMapping("/consol/task/close")
    public void closeTasks(@RequestParam String id1, Model model) {
        // String sid = id1;
        int intid = Integer.parseInt(id1); //преобразовываем строку в число.
        Long id = (long) intid;
        if (!tasksRepository.existsById(id)) {
            System.out.println("id does not exist");
        }
        Tasks task = tasksRepository.findById(id).orElseThrow();
        task.setStatus(Status.CLOSED);
        tasksRepository.save(task);

    }

/*
   @PostMapping ("consol/task/update/priority")
  //  @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void updateTasksConsol( @RequestParam String id1, String priority1, Model model) {

        int  intid = Integer.parseInt(id1); //преобразовываем строку в число.
        Long id =(long)intid;

        if(!tasksRepository.existsById(id)){
            System.out.println("id does not exist");
        }
        Tasks tasks1 = tasksRepository.findById(id).orElseThrow();

        if (Objects.equals(priority1,"1")){

            tasks1.setPriority(Priority.HIGH);

        }else if (Objects.equals(priority1,"2")) {

           tasks1.setPriority(Priority.MEDIUM);

        }else
        {
            tasks1.setPriority(Priority.LOW);

        }
        tasksRepository.save(tasks1);

    }*/

    //---------------------------> попробуем принять json из десктоп риложения <------------------------------
    // @PostMapping ("consol/task/update/priority")

    // @RequestMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    // @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    // @RequestMapping(value = "consol/task/update/priority", method= RequestMethod.POST)

    @PostMapping(value = "consol/task/update/priority")
    public void updateTasksConsolJson(@RequestBody Tasks jsonTaskObject, Model model) throws JsonProcessingException {

        if (!tasksRepository.existsById(jsonTaskObject.getId())) {
            System.out.println("id does not exist");
        }

        Tasks tasks1 = tasksRepository.findById(jsonTaskObject.getId()).orElseThrow();

        if (Objects.equals(jsonTaskObject.getPriority().toString(), "HIGH")) {
            tasks1.setPriority(Priority.HIGH);
        } else if (Objects.equals(jsonTaskObject.getPriority().toString(), "MEDIUM")) {
            tasks1.setPriority(Priority.MEDIUM);
        } else {
            tasks1.setPriority(Priority.LOW);
        }
        tasksRepository.save(tasks1);
    }
    //создадим новый контроллер для обработки React запроса на добавление таск
    @PostMapping(value = "react/addtask")
    public void addTasksReactJson(@RequestBody Tasks jsonTaskObject, Model model) throws JsonProcessingException {

        Tasks newTasks = jsonTaskObject;
        String task = newTasks.getTask();
        Tasks tasks = new Tasks(Status.OPEN, task, Priority.HIGH);
      //  Tasks tasks = new Tasks(Status.OPEN, jsonTaskObject.getTask(), Priority.LOW);
     //   System.out.println(jsonTaskObject.getTask());
         tasksRepository.save(tasks);
    }


    //создадим новый контроллер для обработки React запроса на добавление таск
    @PostMapping(value = "react/deltask")
    public void delTasksReactJson(@RequestBody Tasks jsonTaskObject, Model model) throws JsonProcessingException {

        Tasks newTasks = jsonTaskObject;
        Long id = newTasks.getId();
        tasksRepository.deleteById(id);
      //  Tasks tasks = new Tasks(Status.OPEN, task, Priority.LOW);
        //  Tasks tasks = new Tasks(Status.OPEN, jsonTaskObject.getTask(), Priority.LOW);
        //   System.out.println(jsonTaskObject.getTask());
      //  tasksRepository.save(tasks);
    }
//new controllet for React app - task edit
@PostMapping(value = "react/edittask")
public void editTasksReactJson(@RequestBody Tasks jsonTaskObject, Model model) throws JsonProcessingException {

            Tasks newTasks = jsonTaskObject;
            Long id = newTasks.getId();
         //   Status status = newTasks.getStatus();
            String task = newTasks.getTask();
         //   Priority priority = newTasks.getPriority();

                    Tasks tasks1 = tasksRepository.findById(id).orElseThrow();
                   // model.addAttribute("listOfTasks", tasks1);
                 //   tasks1.setStatus(status);
                    tasks1.setTask(task);
                 //   tasks1.setPriority(priority);
                    tasksRepository.save(tasks1);

    }

    @PostMapping(value = "react/editstatus")
    public void statusTasksReactJson(@RequestBody Tasks jsonTaskObject, Model model) throws JsonProcessingException {

        Tasks newTasks = jsonTaskObject;
        Long id = newTasks.getId();
        Status status = newTasks.getStatus();

        Tasks tasks1 = tasksRepository.findById(id).orElseThrow();
                   if (Objects.equals(status, Status.OPEN)){
                       tasks1.setStatus(Status.CLOSED);
                       tasks1.setPriority(Priority.LOW);
                    }else {
                       tasks1.setStatus(Status.OPEN);
                       tasks1.setPriority(Priority.HIGH);
                   }
          tasksRepository.save(tasks1);

    }



}



