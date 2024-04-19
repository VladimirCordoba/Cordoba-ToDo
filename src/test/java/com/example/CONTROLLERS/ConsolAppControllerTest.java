package com.example.CONTROLLERS;

import com.example.models.Task;
import com.example.repo.TasksRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Optional;

import static com.example.Priority.HIGH;
import static com.example.Status.OPEN;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@ExtendWith(MockitoExtension.class)


public class ConsolAppControllerTest {

@InjectMocks
ConsolAppController consolAppController = new ConsolAppController();

  ObjectMapper objectMapper = new ObjectMapper();

 MockMvc sut = MockMvcBuilders.standaloneSetup(consolAppController).build();

@Mock
  TasksRepository tasksRepositoryMoc;

@Test
   public void statusTasksReactJson() throws Exception {


    Optional<Task> existingTask = Optional.of( new Task(4053L,OPEN,"tasktext", HIGH, 47L));
        String input = objectMapper.writeValueAsString(existingTask.get());
    Mockito.when(tasksRepositoryMoc.findById(4053L)).thenReturn(existingTask);

        sut.perform(post("/react/editstatus")

                .contentType(MediaType.APPLICATION_JSON)
                .content(input));

       // verify(tasksRepositoryMoc, times(1)).save(existingTask);

    }
}
