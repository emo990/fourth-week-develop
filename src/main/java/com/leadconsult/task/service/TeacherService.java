package com.leadconsult.task.service;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.Teacher;
import com.leadconsult.task.entity.enums.GroupName;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Optional<Teacher> getTeacherById(Long id);

    void deleteTeacher(Long id);

    Teacher saveTeacher(Teacher teacher);

    Teacher updateTeacher(Teacher teacher);

    ResponseEntity<List<Teacher>> findAllByGroupNameAndCourseName(String courseName, GroupName groupName);

    Optional<Teacher> findById(Long id);
}
