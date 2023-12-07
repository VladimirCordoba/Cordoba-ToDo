package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping
public class TodoMessageController {

    private final static Map<String, String> posts = new HashMap<>();

    String s;

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
    public Object  addPost(@RequestParam String task1, @RequestParam String status){

    s=status;
        posts.put(task1, status);
      /*return ("/index");*/
    return posts;
    }

    //тут мы будем ненять ключ - таск выполнен

    @PostMapping("/closeTask")
    public Object  changeStatus(@RequestParam String task1 /*@RequestParam String status*/){
        posts.put(task1, s);
        /*return ("/index");*/
        return posts;
    }



    @GetMapping(path = "task")
    public String convertWithStream() {
        String mapAsString = posts.keySet().stream()
                .map(key -> key + ":" + posts.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    /*@GetMapping(path = "task")
    public Map<String, String> taskMessage() {

    return posts;
    }*/



}
