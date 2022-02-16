package com.leadconsult.task.entity;

import com.leadconsult.task.entity.enums.GroupName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends BaseEntity {
    @NotBlank
    private String name;
    @Positive
    private Integer age;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Course> courses;

    @Column(name = "group_name")
    @Enumerated(EnumType.STRING)
    private GroupName groupName;

    public Teacher(String name, Integer age, GroupName groupName, Set<Course> courses) {
        this.name = name;
        this.age = age;
        this.groupName = groupName;
        this.courses = courses;
    }

    public Teacher() {
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

    public void setCourses(Set<Course> course) {
        this.courses = course;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }
}
