package com.leadconsult.task.repository;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.init.TestEntityCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest extends TestEntityCreator {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testFindAllByCourses_CourseName() {

        String courseName = "JAVATEST";

        int expectedStudentsCount = studentRepository.findAllByCourses_name(courseName.toUpperCase()).size() + 1;

        Student student = createStudent("TestStudent", 100, GroupName.C);
        Course course = createCourse(courseName, CourseType.MAIN);
        courseRepository.save(course);
        student.setCourses(Set.of(course));
        studentRepository.save(student);
        int actualStudentsCount = studentRepository.findAllByCourses_name(courseName.toUpperCase()).size();
        assertNotNull(studentRepository);
        assertEquals(expectedStudentsCount, actualStudentsCount);
    }

    @Test
    void testFindAllByCourses_nameAndAgeGreaterThan() {
        assertNotNull(studentRepository);

        String courseName = "JAVATEST";
        int studentCount = studentRepository.findAllByCourses_nameAndAgeGreaterThan("JAVA".toUpperCase(), 15).size();
        Student student = createStudent("TestStudent", 40, GroupName.C);
        Course course = createCourse(courseName, CourseType.MAIN);
        courseRepository.save(course);
        student.setCourses(Set.of(course));
        studentRepository.save(student);

        List<Student> studentsList =
                studentRepository.findAllByCourses_nameAndAgeGreaterThan(courseName.toUpperCase(), 15);
        assertFalse(studentsList.isEmpty());

        int actualStudentCount = studentRepository.findAllByCourses_nameAndAgeGreaterThan(courseName.toUpperCase(), 15).size();
        assertEquals(studentCount + 1, actualStudentCount);

        studentsList = studentRepository.findAllByCourses_nameAndAgeGreaterThan(courseName.toUpperCase(), 50);
        assertTrue(studentsList.isEmpty());

        studentsList = studentRepository.findAllByCourses_nameAndAgeGreaterThan("NOT EXISTING".toUpperCase(), 15);
        assertTrue(studentsList.isEmpty());

    }

    @Test
    void testFindAllByGroupName() {
        assertNotNull(studentRepository);

        List<Student> studentsByGroup = studentRepository.findAllByGroupName(GroupName.A);

        Student student = createStudent("TestStudent", 100, GroupName.A);
        studentRepository.save(student);

        List<Student> actualStudentsByGroup = studentRepository.findAllByGroupName(GroupName.A);
        assertFalse(actualStudentsByGroup.isEmpty());

        assertEquals(studentsByGroup.size() + 1, actualStudentsByGroup.size());
    }

    @Test
    void testFindAllByGroupNameAndCourses_name() {
        List<Student> studentsList = studentRepository.findAllByCourses_nameAndGroupName("JAVATEST".toUpperCase(), GroupName.A);

        Student student = createStudent("TestStudent", 100, GroupName.A);
        Course course = createCourse("JAVATEST", CourseType.MAIN);
        courseRepository.save(course);
        student.setCourses(Set.of(course));
        studentRepository.save(student);

        List<Student> actualStudentsList = studentRepository.findAllByCourses_nameAndGroupName("JAVATEST".toUpperCase(), GroupName.A);

        assertFalse(actualStudentsList.isEmpty());
        assertEquals(studentsList.size() + 1, actualStudentsList.size());

        studentsList = studentRepository.findAllByCourses_nameAndGroupName("NOT EXISTING".toUpperCase(), GroupName.A);
        assertTrue(studentsList.isEmpty());
    }

    @Test
    void testFindById() {
        Student student = createStudent("TestStudent", 100, GroupName.C);
        studentRepository.save(student);
        Long id = student.getId();

        String expectedName = student.getName();
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.ifPresent(std -> assertEquals(expectedName, student.getName()));

        long notExistingId = 10000;
        Optional<Student> optionalStudent2 = studentRepository.findById(notExistingId);
        optionalStudent2.ifPresent(std -> fail());

    }
}