package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping
public class TodoMessageController {
    private final static List<String > posts = new ArrayList<>();

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
    public Object  addPost(@RequestBody String text){
        posts.add(text);
      return ("/index");
}
    @GetMapping(path = "task")
    public List<String> taskMessage() {

    return posts;
    }









}
