package com.leadconsult.task.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExceptionHandler() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = new Student();

        mockMvc.perform(post("/students/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        student))).andExpect(status().is4xxClientError());

        Teacher teacher = new Teacher();

        mockMvc.perform(post("/teachers/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        teacher))).andExpect(status().is4xxClientError());
    }
}