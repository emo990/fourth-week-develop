package com.leadconsult.task.controllers;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.Teacher;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> allTeachers = teacherService.getAllTeachers();

        return ResponseEntity.
                ok(allTeachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Optional<Teacher> student = teacherService.getTeacherById(id);

        if (student.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        }
        return ResponseEntity.
                ok(student.get());

    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping("/update/{id}")
    public Teacher updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);

    }

    @PostMapping("/add")
    Teacher createTeacher(@Valid
                          @RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @GetMapping("/course/{courseName}/group/{groupName}")
    public ResponseEntity<List<Teacher>> findAllByGroupNameAndCourseName(
            @PathVariable("courseName") String courseName,
            @PathVariable("groupName") String groupName) {
        return teacherService
                .findAllByGroupNameAndCourseName(courseName.toUpperCase(), GroupName.valueOf(groupName.toUpperCase()));
    }
}
