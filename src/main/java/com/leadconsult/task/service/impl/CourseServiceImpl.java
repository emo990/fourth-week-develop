package com.leadconsult.task.service.impl;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.repository.CourseRepository;
import com.leadconsult.task.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ResponseEntity<List<Course>> findAllByType(CourseType type) {
        List<Course> allCourses = courseRepository.findAllByType(type);

        return ResponseEntity.
                ok(allCourses);
    }
}
