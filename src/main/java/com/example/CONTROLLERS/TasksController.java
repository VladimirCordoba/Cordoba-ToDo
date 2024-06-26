package com.example.CONTROLLERS;

import com.example.ArrangedList;
import com.example.Priority;
import com.example.Status;
import com.example.models.Task;
import com.example.repo.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TasksController {
    @Autowired
    private TasksRepository tasksRepository;
 //  private ArrangedList arrangedList = new ArrangedList();



    @GetMapping
    public String tasksList(Model model) {

        return "index";
    }

    @PostMapping("/task/create")
    public Object addTasks(@RequestParam String task, Model model) {

      Task tasks = new Task(Status.OPEN, task, Priority.HIGH);
       tasksRepository.save(tasks);

      //  Iterable<Tasks> tasks1 = tasksRepository.findAll();
       // model.addAttribute("listOfTasks", tasks1);
       // model.addAttribute("listOfTasks", new ArrangedList().arrange(tasksRepository));
        model.addAttribute("listOfTasks", ArrangedList.arrange(tasksRepository));


        /*return "redirect:/result";*/
        return "result";
    }

    @PostMapping("/task/delate")
    public String taskDelate(@RequestParam Long id, Model model) {
        tasksRepository.deleteById(id);
        // model.addAttribute("listOfTasks", tasks);
       // Iterable<Tasks> tasks1 = tasksRepository.findAll();

        model.addAttribute("listOfTasks", ArrangedList.arrange(tasksRepository));
        return "result";
    }

    @PostMapping("/task/statusToX")
    public String taskStatusToX(@RequestParam Long id, Model model) {
        if(!tasksRepository.existsById(id)){
            return"result";
        }
        Task task = tasksRepository.findById(id).orElseThrow();
        task.setStatus(Status.CLOSED);
         tasksRepository.save(task);
        // model.addAttribute("listOfTasks", tasks);
      //  Iterable<Tasks> tasks1 = tasksRepository.findAll();
        model.addAttribute("listOfTasks", ArrangedList.arrange(tasksRepository));
        return "result";

    }

    @PostMapping("/task/list")  // Тут получаем список всех тасеов из Базы
    public Object tasksAllTasksList1(Model model) {
    // ArrayList<Tasks> tasksList = new ArrayList<>();        //?

      //  Iterable<Tasks> tasks1 = tasksRepository.findAll();
           //    tasksRepository.findAll().forEach(tasksList::add);
//Comparator<Tasks> compareByPriority = Comparator.comparing(Tasks::getPriority );
//ArrayList<Tasks> sortedTasks =tasksList.stream().sorted(compareByPriority).collect(Collectors
     //   .toCollection(ArrayList::new));
       // tasksList.add((Tasks) tasks1);                                   //?
       // model.addAttribute("listOfTasks", tasks1);
       // model.addAttribute("listOfTasks", tasksList);
        model.addAttribute("listOfTasks", ArrangedList.arrange(tasksRepository));

        /*return "redirect:/result";*/
        return "result";
    }

    @GetMapping("/task/edit")
    public Object editTasks(@RequestParam Long id, Model model) {
        if(!tasksRepository.existsById(id)){
            return"result";
        }
       Task task1 = tasksRepository.findById(id).orElseThrow();
        model.addAttribute("listOfTasks", task1);
      // tasks1.setStatus("X");
     //  tasks1.setTask(task);
      // tasksRepository.save(tasks1);

        //return "redirect:/taskEdit";
        return "taskEdit";
    }
    @PostMapping("/task/{id}/update")
    public Object updateTasks(@PathVariable (value="id") long id, @RequestParam String task, Status status, Priority priority, Model model) {
        if(!tasksRepository.existsById(id)){
            return"result";
        }
        Task task1 = tasksRepository.findById(id).orElseThrow();
        model.addAttribute("listOfTasks", task1);
        task1.setStatus(status);
        task1.setTask(task);
        task1.setPriority(priority);
        tasksRepository.save(task1);

       // return "redirect:/taskEdit";
        return "taskEdit";
    }
   /* @GetMapping ("/consol/task/list")  // Тут получаем список всех тасеов из Базы для консольног приложения
    // @RequestMapping("/consol/task/list")
    public void  consolAllTasksList1(Model model){
        //  ArrangedList.arrange(tasksRepository);

        System.out.println(ArrangedList.arrange(tasksRepository));
    }*/

}