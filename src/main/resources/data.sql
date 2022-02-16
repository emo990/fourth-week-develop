INSERT INTO courses (name, course_type)
VALUES ('JAVA', 'MAIN');
INSERT INTO courses (name, course_type)
VALUES ('MUSIC', 'SECONDARY');

INSERT INTO students (age, group_name, name)
VALUES (25, 'A', 'Ivan');
INSERT INTO students (age, group_name, name)
VALUES (35, 'B', 'Vasil');
INSERT INTO students (age, group_name, name)
VALUES (45, 'C', 'Emil');

INSERT INTO students_courses(student_id, courses_id)
VALUES (1, 2);
INSERT INTO students_courses(student_id, courses_id)
VALUES (2, 2);
INSERT INTO students_courses(student_id, courses_id)
VALUES (3, 1);

INSERT INTO teachers (age, group_name, name)
VALUES (45, 'A', 'Todorov');
INSERT INTO teachers (age, group_name, name)
VALUES (35, 'B', 'Petrov');

INSERT INTO teachers_courses(teacher_id, courses_id)
VALUES (1, 1);
INSERT INTO teachers_courses(teacher_id, courses_id)
VALUES (2, 2);