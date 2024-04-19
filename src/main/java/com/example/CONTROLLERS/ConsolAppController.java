package com.example.CONTROLLERS;


import com.example.ArrangedList;
import com.example.Priority;
import com.example.Status;
import com.example.models.Task;
import com.example.repo.TasksRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static com.example.Priority.HIGH;
import static com.example.Status.OPEN;

@RestController  // поменял @Controller на @RestController поскольку он возвращает JSON
// @Controller
public class ConsolAppController {

    @Autowired
    private TasksRepository tasksRepository;


    //------------> Тут получаем список всех тасеов из Базы для консольного приложения <--------------

    @GetMapping("/consol/task/list")

    public ArrayList consolAllTasksList1(Model model) {

        return (ArrangedList.arrange(tasksRepository));
    }

    @PostMapping("/consol/task/create")
    public void addTasks(@RequestParam String task, Model model) {

        Task task11 = tasksRepository.findById(tasksRepository.count()).orElseThrow();

        Task tasks = new Task(Status.OPEN, task, Priority.LOW);
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
        Task task = tasksRepository.findById(id).orElseThrow();
        task.setStatus(Status.CLOSED);
        tasksRepository.save(task);

    }


    //---------------------------> попробуем принять json из десктоп риложения <------------------------------


    @PostMapping(value = "consol/task/update/priority")
    public void updateTasksConsolJson(@RequestBody Task jsonTaskObject, Model model) throws JsonProcessingException {

        if (!tasksRepository.existsById(jsonTaskObject.getId())) {
            System.out.println("id does not exist");
        }

        Task task1 = tasksRepository.findById(jsonTaskObject.getId()).orElseThrow();

        if (Objects.equals(jsonTaskObject.getPriority().toString(), "HIGH")) {
            task1.setPriority(Priority.HIGH);
        } else if (Objects.equals(jsonTaskObject.getPriority().toString(), "MEDIUM")) {
            task1.setPriority(Priority.MEDIUM);
        } else {
            task1.setPriority(Priority.LOW);
        }
        tasksRepository.save(task1);
    }

    //-------------------->  новый контроллер для обработки React запроса на добавление таск <-------------------------


    @PostMapping(value = "react/addtask")
    public void addTasksReactJson(@RequestBody Task jsonTaskObject, Model model) throws JsonProcessingException {

        Task newTask = jsonTaskObject;
        String task = newTask.getTask();

        Long priorityNew = ArrangedList.maxPriorityNewMethods(tasksRepository) + 1;

        Task tasks = new Task(Status.OPEN, task, Priority.HIGH, priorityNew);

        tasksRepository.save(tasks);
    }

    //---------------------------> создадим новый контроллер для обработки React запроса на добавление таск <------------------------------

    @PostMapping(value = "react/deltask")
    public void delTasksReactJson(@RequestBody Task jsonTaskObject, Model model) throws JsonProcessingException {

        Task newTask = jsonTaskObject;
        Long id = newTask.getId();
        tasksRepository.deleteById(id);

    }
    //---------------------------> new controllet for React app - task edit  <------------------------------


    @PostMapping(value = "react/edittask")
    public void editTasksReactJson(@RequestBody Task jsonTaskObject, Model model) throws JsonProcessingException {

        Task newTask = jsonTaskObject;
        Long id = newTask.getId();

        String task = newTask.getTask();


        Task task1 = tasksRepository.findById(id).orElseThrow();

        task1.setTask(task);

        tasksRepository.save(task1);

    }

    @PostMapping(value = "react/editstatus")
    public void statusTasksReactJson(@RequestBody Task request, Model model) throws Exception {


        Long id = request.getId();

        Status status = request.getStatus();

     Optional<Task> foundTask = tasksRepository.findById(id);
     if (foundTask.isEmpty()){
         throw new Exception("No task in database with id:"+Long.toString(id));
     }
     Task existingTask = foundTask.get();


    //  task = tasksRepository.findById(id).orElse(null);
      //  task = tasksRepository.findById(id).isPresent() ?
              //  tasksRepository.findById(id).get() : null;
                // tasksRepository.findById(id).get() : new Task(4053L,OPEN,"Таска не существует", HIGH, 47L);


        Long priorityNew = tasksRepository.count() + 1;
        if (Objects.equals(status, Status.OPEN)) {
            existingTask.setStatus(Status.CLOSED);
            existingTask.setPriorityNew((long) 1);
        } else {
            existingTask.setStatus(Status.OPEN);

            priorityNew = ArrangedList.maxPriorityNewMethods(tasksRepository) + 1;
            existingTask.setPriorityNew(priorityNew);
        }
        tasksRepository.save(existingTask);

    }

}



