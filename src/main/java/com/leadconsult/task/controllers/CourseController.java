package com.leadconsult.task.controllers;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Course>> getAllCoursesByType(@PathVariable("type") String type) {
        return courseService.findAllByType(CourseType.valueOf(type.toUpperCase()));
    }
}
