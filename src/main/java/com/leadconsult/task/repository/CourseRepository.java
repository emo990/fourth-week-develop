package com.leadconsult.task.repository;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByType(CourseType type);
}
