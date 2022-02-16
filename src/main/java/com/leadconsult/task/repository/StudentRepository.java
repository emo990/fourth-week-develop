package com.leadconsult.task.repository;

import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.enums.GroupName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByCourses_name(String courseName);

    List<Student> findAllByGroupName(GroupName groupName);

    List<Student> findAllByCourses_nameAndAgeGreaterThan(String courseName, Integer Age);

    List<Student> findAllByCourses_nameAndGroupName(String courseName, GroupName groupName);

    Optional<Student> findById(Long id);

}