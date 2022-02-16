package com.leadconsult.task.init;

import com.leadconsult.task.entity.Course;
import com.leadconsult.task.entity.Student;
import com.leadconsult.task.entity.Teacher;
import com.leadconsult.task.entity.enums.CourseType;
import com.leadconsult.task.entity.enums.GroupName;
import com.leadconsult.task.repository.CourseRepository;
import com.leadconsult.task.repository.StudentRepository;
import com.leadconsult.task.repository.TeacherRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class TestEntityCreator {

    public Student createStudent(String name, int age, GroupName groupName) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setGroupName(groupName);
        return student;
    }

    public Course createCourse(String name, CourseType type) {
        Course course = new Course();
        course.setName(name);
        course.setType(type);

        return course;
    }

    public Teacher createTeacher(String name, int age, GroupName groupName) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setAge(age);
        teacher.setGroupName(groupName);

        return teacher;
    }

//    private final static String COURSE_1 = "JAVA";
//    private final static String COURSE_2 = "SPRING";
//    private final static String COURSE_3 = "SPORT";
//    private final static String COURSE_4 = "MUSIC";
//
//    private final CourseRepository courseRepository;
//    private StudentRepository studentRepository;
//    private TeacherRepository teacherRepository;
//
//    public TestDataCreator(CourseRepository courseRepository, TeacherRepository teacherRepository) {
//        this.courseRepository = courseRepository;
//        this.teacherRepository = teacherRepository;
//    }
//
//    public TestDataCreator(CourseRepository courseRepository, StudentRepository studentRepository) {
//        this.courseRepository = courseRepository;
//        this.studentRepository = studentRepository;
//    }
//
//    public TestDataCreator(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//
//    }
//
//    public List<Course> createCourse() {
//        Course testCourse1 = new Course(COURSE_1, CourseType.MAIN);
//        Course testCourse2 = new Course(COURSE_2, CourseType.MAIN);
//        Course testCourse3 = new Course(COURSE_3, CourseType.SECONDARY);
//        Course testCourse4 = new Course(COURSE_4, CourseType.SECONDARY);
//        courseRepository.saveAll(List.of(testCourse1, testCourse2, testCourse3, testCourse4));
//        return courseRepository.findAll();
//    }
//
//    public List<Student> createStudents(List<Course> courses) {
//        Student student1 = new Student("Ivan", 20, GroupName.A, Set.of(courses.get(0), courses.get(1)));
//        Student student2 = new Student("Vasil", 22, GroupName.B, Set.of(courses.get(1), courses.get(2)));
//        Student student3 = new Student("Emil", 25, GroupName.C, Set.of(courses.get(2), courses.get(3)));
//        studentRepository.saveAll(List.of(student1, student2, student3));
//        return studentRepository.findAll();
//    }
//
//    public List<Teacher> createTeachers(List<Course> courses) {
//        Teacher teacher1 = new Teacher("Todorov", 35, GroupName.A, Set.of(courses.get(0), courses.get(1)));
//        Teacher teacher2 = new Teacher("Petrov", 45, GroupName.B, Set.of(courses.get(2), courses.get(3)));
//        if (teacherRepository.findAll().size() == 0) {
//            teacherRepository.saveAll(List.of(teacher1, teacher2));
//        }
//        return teacherRepository.findAll();
//    }
}

