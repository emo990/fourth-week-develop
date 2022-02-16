package com.leadconsult.task.service;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.enums.GroupName;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student getStudentById(Long id);

    ResponseEntity<List<Student>> getAllStudents();

    void deleteStudent(Long id);

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    ResponseEntity<List<Student>> findAllByCourseName(String courseName);

    ResponseEntity<List<Student>> findAllByGroupName(GroupName groupName);

    ResponseEntity<List<Student>> findAllByCourseNameAndAge(String courseName, Integer age);

    ResponseEntity<List<Student>> findAllByGroupNameAndCourseName(String courseName, GroupName groupName);

    Optional<Student> findById(Long id);
}
