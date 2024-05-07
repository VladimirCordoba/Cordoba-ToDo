package com.example.CONTROLLERS;

import com.example.ArrangedList;
import com.example.models.Task;
import com.example.repo.TasksRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import static com.example.ArrangedList.maxPriorityNewMethods;
import static com.example.Priority.HIGH;
import static com.example.Status.CLOSED;
import static com.example.Status.OPEN;
//import static jdk.jfr.internal.util.Matcher.match;

import static org.mockito.Mockito.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



//@ExtendWith(MockitoExtension.class)
@WebMvcTest(ConsolAppController.class)


public class ConsolAppControllerTest {
/*
    private MockMvc sut;
    private ObjectMapper objectMapper;
    */
/*@InjectMocks
ConsolAppController consolAppController = new ConsolAppController();*/

 //   ArrangedList mockArrangeList = Mockito.mock(ArrangedList.class);

@Autowired
private ObjectMapper objectMapper;
//ObjectMapper objectMapper = new ObjectMapper();
@Autowired
private MockMvc  sut;
//MockMvc  sut = MockMvcBuilders.standaloneSetup(consolAppController).build();

@MockBean
  TasksRepository tasksRepositoryMoc;
//@MockBean
//ArrangedList arrangedList;

@Test
   public void statusTasksReactJson() throws Exception {


    Optional<Task> existingTask = Optional.of( new Task(4053L,OPEN,"tasktext", HIGH, 47L));
    String input = objectMapper.writeValueAsString(existingTask.get());
    //  Mockito.when(tasksRepositoryMoc.findById(4053L)).thenReturn(existingTask);
    Mockito.when(tasksRepositoryMoc.findById(Mockito.any())).thenReturn(existingTask);
        sut.perform(post("/react/editstatus")

                .contentType(MediaType.APPLICATION_JSON)
                .content(input));

                  Assertions.assertEquals(existingTask.get().getId(), 4053L);
                  Assertions.assertEquals(existingTask.get().getStatus(), CLOSED);
                  Assertions.assertEquals(existingTask.get().getPriorityNew(), 1);



        verify(tasksRepositoryMoc, times(1)).save(existingTask.get());

    }


   @Test
  public  void addTasksReactJson() throws Exception {


        Optional<Task> existingTask = Optional.of( new Task(4053L,OPEN,"tasktext", HIGH, 47L));
        String input = objectMapper.writeValueAsString(existingTask.get());

      // Mockito.when(tasksRepositoryMoc.findAll()).thenReturn(existingTask.get());
       Mockito.when(tasksRepositoryMoc.findAll()).thenReturn(existingTask.stream().toList());
      // doReturn(existingTask.get()).when(tasksRepositoryMoc.findAll());
     //  doReturn(tasksRepositoryMoc.findAll());




        sut.perform(post("/react/addtask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
       .andExpect(jsonPath("$.priorityNew").value(48));

      //  Assertions.assertEquals(existingTask.get().getId(), 4053L);
      // Assertions.assertEquals(existingTask.get().getPriorityNew(), 48);
      // Assertions.assertEquals(48, existingTask.get().getPriorityNew());
     //  Assertions.assertEquals(48, maxPriorityNewMethods(tasksRepositoryMoc)+1);



    }


}
