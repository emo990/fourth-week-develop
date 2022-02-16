package com.leadconsult.task.service.impl;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.exceptions.FieldErrorMessage;
import com.leadconsult.task.exceptions.ObjectNotFoundException;
import com.leadconsult.task.exceptions.ObjectNotValid;
import com.leadconsult.task.repository.StudentRepository;
import com.leadconsult.task.service.StudentService;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return ResponseEntity.
                ok(allStudents);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(id));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(id));
        studentRepository.deleteById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student updateStudent) {
        studentRepository.findById(updateStudent.getId()).orElseThrow(() ->
                new ObjectNotFoundException(updateStudent.getId()));

        if (updateStudent.getName() != null && updateStudent.getAge() > 0) {
            return saveStudent(updateStudent);
        }
        throw new ObjectNotValid("Student not valid, name must be not blank and age must be positive!");
    }

    @Override
    public ResponseEntity<List<Student>> findAllByCourseName(String courseName) {
        List<Student> students = studentRepository.findAllByCourses_name(courseName.toUpperCase());

        if (students.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        }
        return ResponseEntity.
                ok(students);
    }

    @Override
    public ResponseEntity<List<Student>> findAllByGroupName(GroupName groupName) {
        List<Student> students = studentRepository.findAllByGroupName(groupName);

        if (students.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        }
        return ResponseEntity.
                ok(students);
    }

    @Override
    public ResponseEntity<List<Student>> findAllByCourseNameAndAge(String courseName, Integer age) {
        List<Student> students = studentRepository.findAllByCourses_nameAndAgeGreaterThan(courseName, age);
        if (students.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        }
        return ResponseEntity.
                ok(students);
    }

    @Override
    public ResponseEntity<List<Student>> findAllByGroupNameAndCourseName(String courseName, GroupName groupName) {
        List<Student> students = studentRepository.findAllByCourses_nameAndGroupName(courseName.toUpperCase(), groupName);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }
}
