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


@PostMapping()
    public String  addPost( @RequestParam Post task){
        posts.add(task);
        return "message2";
}
    @GetMapping(path = "message")
    public List<Post> taskMessage() {

    return posts;
    }



}
