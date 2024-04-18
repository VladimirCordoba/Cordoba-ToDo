package com.example.CONTROLLERS;

import com.example.models.Tasks;
import com.example.repo.TasksRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import static com.example.Priority.HIGH;
import static com.example.Status.OPEN;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@ExtendWith(MockitoExtension.class)


public class ConsolAppControllerTest {

@InjectMocks
ConsolAppController consolAppController = new ConsolAppController();

  ObjectMapper objectMapper = new ObjectMapper();



 MockMvc mockMvc = MockMvcBuilders.standaloneSetup(consolAppController).build();

@Mock
  TasksRepository tasksRepositoryMoc;

@Test
   public void statusTasksReactJson() throws Exception {


        Tasks task = new Tasks(4053L,OPEN,"tasktext", HIGH, 47L);
        String taskJson = objectMapper.writeValueAsString(task);


        mockMvc.perform(post("/react/editstatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson));

      //  verify(tasksRepositoryMoc, times(1)).save(task);

    }
}
