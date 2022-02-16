package com.leadconsult.task.entity;

import com.leadconsult.task.entity.enums.CourseType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @NotBlank
    private String name;

    @Column(name = "course_type")
    @Enumerated(EnumType.STRING)
    private CourseType type;

    public Course(String name, CourseType type) {
        this.name = name;
        this.type = type;
    }

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String courseName) {
        this.name = courseName;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

}
