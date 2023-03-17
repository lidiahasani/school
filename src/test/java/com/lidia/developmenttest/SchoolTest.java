package com.lidia.developmenttest;

import com.lidia.developmenttest.model.School;
import com.lidia.developmenttest.model.Student;
import com.lidia.developmenttest.model.Subject;
import com.lidia.developmenttest.model.Teacher;
import com.lidia.developmenttest.service.SchoolService;
import com.lidia.developmenttest.service.StudentService;
import com.lidia.developmenttest.service.SubjectService;
import com.lidia.developmenttest.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Make an application with the above classes for the headmaster to keep track of his
 * school budget
 - Add 4 different Subjects and one Teacher for each Subject
 - Then add a list of students for each Subject.
 - Each student can pay a part of tuition fee or full tuition fee.
 - Each student that has paid a part of tuition fee can continue paying until he has paid full tuition fee (The student can not pay more than full tuition fee – 5000$).
 - Find total earning of school from tuition fees
 - Find total spending of school from teachers’ salary
 - Find net earnings/losses
 - Output the list of students and their paid fees ordered alphabetically into a text file (student.text) and save it on the local PC.
 */

@SpringBootTest
class SchoolTest {

    private final Logger logger = LoggerFactory.getLogger(SchoolTest.class);

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        logger.info("Creating school..");
        School school = new School();

        logger.info("Creating teachers..");
        var teacher1 = new Teacher("Jane", 500);
        var teacher2 = new Teacher("Anne", 200);
        var teacher3 = new Teacher("John", 100);
        var teacher4 = new Teacher("Jimmy", 300);

        logger.info("Adding teachers to school..");
        school.addTeacher(teacher1);
        school.addTeacher(teacher2);
        school.addTeacher(teacher3);
        school.addTeacher(teacher4);

        logger.info("Creating subjects..");
        var subject1 = new Subject("Math", teacher1);
        var subject2 = new Subject("English", teacher2);
        var subject3 = new Subject("Physics", teacher3);
        var subject4 = new Subject("Biology", teacher4);

        logger.info("Creating students..");
        var student1 = new Student("Linda");
        var student2 = new Student("Maria");
        var student3 = new Student("Jenny");
        var student4 = new Student("Bill");

        logger.info("Adding students to school..");
        school.addStudent(student1);
        school.addStudent(student2);
        school.addStudent(student3);
        school.addStudent(student4);

        logger.info("Adding students to subjects..");
        subject1.setStudents(List.of(student1, student2));
        subject2.setStudents(List.of(student1, student3));
        subject3.setStudents(List.of(student2, student4));
        subject4.setStudents(List.of(student3, student4));

        var subjects = List.of(subject1, subject2, subject3, subject4);

        logger.info("Saving all subjects..");
        subjectService.saveAll(subjects);

        logger.info("Doing some assertions..");
        assertEquals(4, studentService.getAllStudentsOrderedAlphabetically(1).size());
        assertEquals(4, subjectService.getAllSubjects().size());
        assertEquals(4, teacherService.getAllTeachers().size());

        logger.info("Paying tuition fees for students..");
        studentService.payFee(1, 1000);
        studentService.payFee(2, 3000);
        studentService.payFee(3, 2500);
        studentService.payFee(4, 1800);

        logger.info("The following fee cannot proceed..");
        studentService.payFee(1, 4500);

        logger.info("Paying an allowed tuition fee..");
        studentService.payFee(2, 1500);

        logger.info("Retrieving a school and calculating earnings on the fly");
        var updatedSchool = schoolService.findSchoolWithEarnings(1);

        var totalEarnings = updatedSchool.getTotalMoneyEarned();
        logger.info("Total earnings: {}", totalEarnings);

        var totalSpending = updatedSchool.getTotalMoneySpent();
        logger.info("Total spending: {}", totalSpending);

        var netResult = totalEarnings - totalSpending;
        if (netResult >= 0) {
            logger.info("Net earnings: {}", netResult);
        } else {
            logger.info("Net loss: {}", Math.abs(netResult));
        }

        logger.info("Outputting all students and their fees to file");
        var students = studentService.getAllStudentsOrderedAlphabetically(1);
        studentService.writeToFile(students);
    }

}
