package com.leadconsult.task.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.Teacher;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.init.TestEntityCreator;
import com.leadconsult.task.repository.CourseRepository;
import com.leadconsult.task.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest extends TestEntityCreator {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testGetAllTeachers() throws Exception {
        int teachersCount = teacherRepository.findAll().size();

        Teacher teacher = createTeacher("TODOROV", 50, GroupName.A);
        teacherRepository.save(teacher);

        mockMvc.perform(get("/teachers/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(teachersCount + 1)));
    }

    @Test
    void testGetTeacherById() throws Exception {
        Teacher teacher = createTeacher("TeacherTest", 20, GroupName.C);
        teacherRepository.save(teacher);

        List<Teacher> teacherList = teacherRepository.findAll();
        String teacherName = teacherList.get(0).getName();

        mockMvc.perform(get("/teachers/" + teacherList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(teacherName));

        int notExistingId = 10000;

        mockMvc.perform(get("/teachers/" + notExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTeacher() throws Exception {
        Teacher teacher = createTeacher("TestTeacher", 100, GroupName.A);
        teacherRepository.save(teacher);
        List<Teacher> teacherList = teacherRepository.findAll();
        long id = teacherList.get(0).getId();

        mockMvc.perform(delete("/teachers/delete/" + id))
                .andExpect(status().isOk());

        long notExistingId = 10000;

        mockMvc.perform(delete("/teachers/delete/" + notExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateTeacher() throws Exception {
        Teacher oldTeacher = createTeacher("Old teacher Test", 50, GroupName.B);
        Long oldTeacherId = teacherRepository.save(oldTeacher).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        Teacher teacher = createTeacher("Updated name Test", 100, GroupName.A);
        teacher.setId(oldTeacherId);
        mockMvc.perform(put("/teachers/update/" + oldTeacherId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        teacher)));

        long notExistingId = 10000;

        mockMvc.perform(put("/students/update/" + notExistingId))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testCreateTeacher() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Teacher teacher = createTeacher("TestTeacher", 100, GroupName.A);

        mockMvc.perform(post("/teachers/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(
                                teacher)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllByGroupNameAndCourseName() throws Exception {
        int countByCourseAndGroup = teacherRepository.findAllByCourses_nameAndGroupName("JAVATEST", GroupName.A).size();

        Teacher teacher = createTeacher("TeacherTest", 100, GroupName.A);
        Course course = createCourse("JAVATEST", CourseType.MAIN);
        courseRepository.save(course);
        teacher.setCourses(Set.of(course));
        teacherRepository.save(teacher);

        GroupName groupA = GroupName.A;
        GroupName groupC = GroupName.C;

        String courseJava = "JAVATEST";
        String notExistingCourse = "TestCourse";

        mockMvc.perform(get("/teachers/course/" + courseJava + "/group/" + groupA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(countByCourseAndGroup + 1)));

        mockMvc.perform(get("/teachers/course/" + notExistingCourse + "/group/" + groupC))
                .andExpect(status().isNotFound());
    }
}