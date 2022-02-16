package com.leadconsult.task.service;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.enums.CourseType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    ResponseEntity<List<Course>> findAllByType(CourseType type);
}
