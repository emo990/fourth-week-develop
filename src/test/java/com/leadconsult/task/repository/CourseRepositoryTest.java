package com.leadconsult.task.repository;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.init.TestEntityCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest extends TestEntityCreator {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findAllByType() {
        List<Course> allByTypeMain = courseRepository.findAllByType(CourseType.MAIN);
        List<Course> allByTypeSECONDARY = courseRepository.findAllByType(CourseType.SECONDARY);

        Course course1 = createCourse("JAVA".toUpperCase(), CourseType.MAIN);
        Course course2 = createCourse("SPRING".toUpperCase(), CourseType.SECONDARY);
        courseRepository.saveAll(List.of(course1, course2));

        assertEquals(allByTypeMain.size() + 1, courseRepository.findAllByType(CourseType.MAIN).size());
        assertEquals(allByTypeSECONDARY.size() + 1, courseRepository.findAllByType(CourseType.SECONDARY).size());
    }

}