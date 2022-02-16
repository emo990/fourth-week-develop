package com.leadconsult.task.controllers;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentByID(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/course/{courseName}")
    public ResponseEntity<List<Student>> getStudentsByCourseName(@PathVariable("courseName") String courseName) {
        return studentService.findAllByCourseName(courseName.toUpperCase());
    }

    @GetMapping("/course/{courseName}/age/{age}")
    public ResponseEntity<List<Student>> getAllByCourseNameAndAge(
            @PathVariable("courseName") String courseName,
            @PathVariable("age") Integer age) {
        return studentService.findAllByCourseNameAndAge(courseName, age);
    }

    @GetMapping("/group/{groupName}")
    public ResponseEntity<List<Student>> getStudentsByGroupName(@PathVariable("groupName") String groupName) {
        return studentService.findAllByGroupName(GroupName.valueOf(groupName.toUpperCase()));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @PostMapping("/add")
    public Student createStudent(@Valid
                                 @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/course/{courseName}/group/{groupName}")
    public ResponseEntity<List<Student>> findAllByGroupNameAndCourseName(@PathVariable("courseName") String courseName,
                                                                         @PathVariable("groupName") String groupName) {
        return studentService
                .findAllByGroupNameAndCourseName(courseName.toUpperCase(), GroupName.valueOf(groupName.toUpperCase()));
    }

}
