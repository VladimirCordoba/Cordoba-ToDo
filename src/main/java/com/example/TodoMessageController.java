package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping
public class TodoMessageController {
    private final static List<Post> posts = new ArrayList<>();

   /* @GetMapping(path = "message")
    public List<String> taskMessage() {
        return List.of("Todo Message");
    }
    */


@PostMapping("/task/create")
    public Object  addPost( @RequestParam Post task){
        posts.add(task);
      return posts;
}
    @GetMapping(path = "task")
    public List<Post> taskMessage() {

    return posts;
    }



}
