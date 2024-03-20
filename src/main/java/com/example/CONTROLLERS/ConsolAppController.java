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
 //  public void updateTasksConsolJson( @RequestBody String jsonTaskObject, Model model) throws IOException, URISyntaxException {

    public void updateTasksConsolJson( @RequestBody Tasks jsonTaskObject, Model model) throws JsonProcessingException {
     //   String url = jsonTaskObject;
     //  String result = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
     //  String result = jsonTaskObject;
     //   System.out.println(jsonTaskObject1);

        // if ( jsonTaskObject != null){
          //   Tasks editedTaks = jsonTaskObject;

        // }
    //   URI jsonUrl = new URI(jsonTaskObject);

        ObjectMapper objectMapper = new ObjectMapper();

      //   Tasks editedTaks = objectMapper.readValue(editedTask, Tasks.class);
     //  Tasks editedTaks = objectMapper.readValue(jsonUrl.toURL(), Tasks.class);
     //  System.out.println(" Task to string ="+ editedTaks.toString());

       if (!tasksRepository.existsById(jsonTaskObject.getId())) {     //<--------------5

           System.out.println("id does not exist");
        }
        Tasks tasks1 = tasksRepository.findById(jsonTaskObject.getId()).orElseThrow();//<--------------5
      // Tasks tasks1 = tasksRepository.findById((long)4053).orElseThrow();

        if (Objects.equals(jsonTaskObject.getPriority().toString(), "HIGH")) {
         //   if (editedTaks.getPriority().toString().equals("HIGH")) {

            tasks1.setPriority(Priority.HIGH);

       // } else if (editedTaks.getPriority().toString().equals("MEDIUM")) {
        } else if (Objects.equals(jsonTaskObject.getPriority().toString(),"MEDIUM")) {

            tasks1.setPriority(Priority.MEDIUM);

        } else {
            tasks1.setPriority(Priority.LOW);

        }
        tasksRepository.save(tasks1);

    }


}
