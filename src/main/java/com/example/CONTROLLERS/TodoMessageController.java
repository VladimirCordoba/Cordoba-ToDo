package com.example.CONTROLLERS;

import com.example.Post;
import com.example.TaskMessage;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

@Controller
//@RestController
@RequestMapping

public class TodoMessageController {

    /*private final static Map<String, String> posts = new HashMap<>();*/
    public TaskMessage[] massivTaskov = new TaskMessage[1];

    TaskMessage taskMessage;
  //  String s;

   /* @GetMapping(path = "message")
    public List<String> taskMessage() {
        return List.of("Todo Message");
    }
    */


/*@PostMapping("/task/create")
    public Object  addPost( @RequestParam Post task1, @RequestBody String text){
        posts.put(task1, text);
      return ("/index");
}
    @GetMapping(path = "task")
    public Map<Post, String> taskMessage() {

    return posts;
    }*/

@PostMapping("/task/create")
    public Object  addPost(
            @RequestParam Integer id, @RequestParam String tMessage, @RequestParam String status,
            ModelMap model)

{
    taskMessage = new TaskMessage(id, tMessage, status);
     massivTaskov[0] = taskMessage;

    List<TaskMessage> spisokTaskov = new ArrayList<>();
    spisokTaskov.add(taskMessage);

    /*s=status;*/
      /*  posts.put(task1, status);*/
      /*return ("/index");*/
   // return massivTaskov;
  //  model.addAttribute("taskMessage", taskMessage);
 //   return massivTaskov;
    return "result";
    }



    //тут мы будем ненять ключ - таск выполнен

    @PostMapping("/closeTask")
    public String changeStatus(@RequestParam String status ) {

        taskMessage.setStatus(status);
        //     posts.put(task1, s);
        massivTaskov[0] = taskMessage;

        return ("index");
       /* return massivTaskov;*/

    }
/*
    @GetMapping(path = "task")
    public String taskMessage(Map<String, Object> model) {
model.put ("some", massivTaskov);
        return "index";
    }*/


   /* @GetMapping(path = "task")
    public String convertWithStream() {
        String mapAsString = posts.keySet().stream()
                .map(key -> key + ":" + posts.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }*/




    @GetMapping("task")
    public String taskMessage(Model model) {
      List<TaskMessage> spisokTaskov = new ArrayList<>();
        spisokTaskov.add(taskMessage);
        model.addAttribute("spisokTaskov",  spisokTaskov);
    return "index";
    }

   /* @GetMapping(path = "task")
    public TaskMessage[] taskMessage() {

        return massivTaskov;
      //  return Arrays.toString(massivTaskov);

    }*/



}