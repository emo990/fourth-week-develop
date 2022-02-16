package com.leadconsult.task.service.impl;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.Teacher;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.exceptions.ObjectNotFoundException;
import com.leadconsult.task.exceptions.ObjectNotValid;
import com.leadconsult.task.repository.TeacherRepository;
import com.leadconsult.task.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(id));
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateTeacher(Teacher updateTeacher) {
        teacherRepository.findById(updateTeacher.getId()).orElseThrow(() ->
                new ObjectNotFoundException(updateTeacher.getId()));

        if (updateTeacher.getName() != null && updateTeacher.getAge() > 0) {
            return saveTeacher(updateTeacher);
        }
        throw new ObjectNotValid("Teacher not valid, name must be not blank and age must be positive!");
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public ResponseEntity<List<Teacher>> findAllByGroupNameAndCourseName(String courseName, GroupName groupName) {
        List<Teacher> teachers = teacherRepository.findAllByCourses_nameAndGroupName(courseName.toUpperCase(), groupName);

        if (teachers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teachers);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }
}
