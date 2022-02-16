package com.leadconsult.task.repository;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.Teacher;
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
class TeacherRepositoryTest extends TestEntityCreator {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void testFindAllByGroupNameAndCourse_name() {
        List<Teacher> teacherList = teacherRepository.findAllByCourses_nameAndGroupName("JAVATEST".toUpperCase(), GroupName.A);

        Teacher teacher = createTeacher("TestStudent", 100, GroupName.A);
        Course course = createCourse("JAVATEST", CourseType.MAIN);
        courseRepository.save(course);
        teacher.setCourses(Set.of(course));
        teacherRepository.save(teacher);

        List<Teacher> actualTeachersList = teacherRepository.findAllByCourses_nameAndGroupName("JAVATEST".toUpperCase(), GroupName.A);

        assertFalse(actualTeachersList.isEmpty());
        assertEquals(teacherList.size() + 1, actualTeachersList.size());

        teacherList = teacherRepository.findAllByCourses_nameAndGroupName("NOT EXISTING".toUpperCase(), GroupName.A);
        assertTrue(teacherList.isEmpty());

    }

    @Test
    void testFindTeacherById() {
        Teacher teacher = createTeacher("TestTeacher", 100, GroupName.C);
        teacherRepository.save(teacher);
        Long id = teacher.getId();

        String expectedName = teacher.getName();
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        optionalTeacher.ifPresent(std -> assertEquals(expectedName, teacher.getName()));

        long notExistingId = 10000;
        Optional<Teacher> optionalStudent2 = teacherRepository.findById(notExistingId);
        optionalStudent2.ifPresent(std -> fail());
    }

}