package com.leadconsult.task.entity;

import com.leadconsult.task.entity.enums.GroupName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column
    @NotBlank
    private String name;
    @Column
    @Positive
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Course> courses;

    @Column(name = "group_name")
    @Enumerated(EnumType.STRING)
    private GroupName groupName;

    public Student(String name, Integer age, GroupName groupName, Set<Course> courses) {
        this.name = name;
        this.age = age;
        this.groupName = groupName;
        this.courses = courses;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }
}
