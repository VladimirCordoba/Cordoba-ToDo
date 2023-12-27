package com.example.CONTROLLERS;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
        }
    @GetMapping
    public String index(){

        return "index";
    }
    @GetMapping("index")
    public String index1() {
        return "index";
    }

    @GetMapping("result")
    public String result1() {
        return "result";
    }

}

