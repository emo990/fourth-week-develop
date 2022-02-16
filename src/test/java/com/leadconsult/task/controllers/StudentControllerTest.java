package com.leadconsult.task.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.init.TestEntityCreator;
import com.leadconsult.task.repository.CourseRepository;
import com.leadconsult.task.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest extends TestEntityCreator {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> allStudents = studentRepository.findAll();
        Student student = createStudent("testStudent", 19, GroupName.A);
        studentRepository.save(student);

        mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allStudents.size() + 1)));
    }

    @Test
    void testGetStudentById() throws Exception {
        Student student = createStudent("StudentTest", 20, GroupName.C);
        studentRepository.save(student);

        List<Student> studentList = studentRepository.findAll();
        String studentName = studentList.get(0).getName();
        Long studentId = studentList.get(0).getId();

        mockMvc.perform(get("/students/" + studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(studentName));

        int notExistingId = 10000;

        mockMvc.perform(get("/students/" + notExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetStudentsByCourseName() throws Exception {
        String courseJava = "JAVATEST";
        int allByNameCount = studentRepository.findAllByCourses_name(courseJava).size();

        Student student = createStudent("IvanTest", 20, GroupName.C);
        Course course = createCourse("JAVATEST", CourseType.MAIN);
        courseRepository.save(course);
        student.setCourses(Set.of(course));
        studentRepository.save(student);

        mockMvc.perform(get("/students/course/" + courseJava))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allByNameCount + 1)));
    }

    @Test
    void testGetAllByCourseNameAndAge() throws Exception {
        String courseName = "JAVATEST".toUpperCase();
        int age = 15;

        int allByNameCount = studentRepository.findAllByCourses_nameAndAgeGreaterThan(courseName.toUpperCase(), age).size();

        Student student = createStudent("IvanTest", 40, GroupName.C);
        Course course = createCourse(courseName.toUpperCase(), CourseType.MAIN);
        courseRepository.save(course);
        student.setCourses(Set.of(course));
        studentRepository.save(student);

        mockMvc.perform(get("/students/course/" + courseName + "/age/" + age))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allByNameCount + 1)));
    }

    @Test
    void testGetStudentsByGroupName() throws Exception {
        GroupName groupA = GroupName.A;
        int allByNameCount = studentRepository.findAllByGroupName(GroupName.A).size();

        Student student = createStudent("IvanTest", 40, GroupName.A);
        studentRepository.save(student);

        mockMvc.perform(get("/students/group/" + groupA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allByNameCount + 1)));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Student student = createStudent("TestStudent", 100, GroupName.A);
        studentRepository.save(student);
        List<Student> studentList = studentRepository.findAll();
        long id = studentList.get(0).getId();

        mockMvc.perform(delete("/students/delete/" + id))
                .andExpect(status().isOk());

        long notExistingId = 10000;

        mockMvc.perform(delete("/students/delete/" + notExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateStudent() throws Exception {
        Student oldStudent = createStudent("Old name Test", 50, GroupName.B);
        Long oldStudentId = studentRepository.save(oldStudent).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = createStudent("Updated name Test", 100, GroupName.A);
        student.setId(oldStudentId);
        mockMvc.perform(put("/students/update/" + oldStudentId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        student)));

        long notExistingId = 10000;

        mockMvc.perform(put("/students/update/" + notExistingId))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateStudent() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = createStudent("StudentTest", 100, GroupName.A);

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(
                                student)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllByGroupNameAndCourseName() throws Exception {
        int countByCourseAndGroup = studentRepository.findAllByCourses_nameAndGroupName("JAVATEST", GroupName.A).size();

        Student student = createStudent("IvanTest", 100, GroupName.A);
        Course course = createCourse("JAVATEST", CourseType.MAIN);
        courseRepository.save(course);
        student.setCourses(Set.of(course));
        studentRepository.save(student);

        GroupName groupA = GroupName.A;
        GroupName groupC = GroupName.C;

        String courseJava = "JAVATEST";
        String notExistingCourse = "TestCourse";

        mockMvc.perform(get("/students/course/" + courseJava + "/group/" + groupA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(countByCourseAndGroup + 1)));

        mockMvc.perform(get("/students/course/" + notExistingCourse + "/group/" + groupC))
                .andExpect(status().isNotFound());
    }
}