package com.leadconsult.task.repository;

import com.leadconsult.task.entity.Teacher;
import com.leadconsult.task.entity.enums.GroupName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByCourses_nameAndGroupName(String courseName, GroupName groupName);

    Optional<Teacher> findById(Long id);
}
