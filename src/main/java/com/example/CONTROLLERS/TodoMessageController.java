package com.example.CONTROLLERS;

import com.example.TaskMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;


//@RestController этот контроллер возвращает JSON поэтому заменил его на просто контроллер тогда маппинг работает.
@Controller
@RequestMapping
public class TodoMessageController {
    TaskMessage taskMessage;
    List<TaskMessage> spisokTaskov = new ArrayList<>();

    //Создаем объекты тасков и добавляем их в список List
@PostMapping("/task/create")
    public Object  addPost(
            @RequestParam Integer id, @RequestParam String tMessage, @RequestParam String status,
            Model model)
        {
            taskMessage = new TaskMessage(id, tMessage, status);
            spisokTaskov.add(taskMessage);
        return "index";
         }

 //тут мы будем ненять ключ - таск выполнен
    @PostMapping("/closeTask")
    public String changeStatus(@RequestParam Integer id, Map<String, Object> model) {

        Iterator<TaskMessage>  iterator = spisokTaskov.iterator();
            while(iterator.hasNext())
            {
                TaskMessage tasks = iterator.next();
                if( (int)tasks.getId() == id)
                {
                tasks.setStatus("x");
                }
            }
              model.put("spisokTaskov", spisokTaskov);
        return ("index");

    }

    // Выводим на экран список тасков
    @GetMapping("task")
    public String taskMessage(Map<String, Object> model) {
            model.put("spisokTaskov", spisokTaskov);
    return "index";
    }

}