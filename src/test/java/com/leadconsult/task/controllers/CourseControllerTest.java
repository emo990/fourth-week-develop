package com.leadconsult.task.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.init.TestEntityCreator;
import com.leadconsult.task.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest extends TestEntityCreator {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testGetCoursesByType() throws Exception {
        int countByType = courseRepository.findAllByType(CourseType.MAIN).size();

        Course course1 = createCourse("JAVA".toUpperCase(), CourseType.MAIN);
        courseRepository.save(course1);


        CourseType typeMain = CourseType.MAIN;

        mockMvc.perform(get("/courses/" + typeMain))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(countByType + 1)));

    }
}