package com.lidia.developmenttest.service;

import com.lidia.developmenttest.model.Student;
import com.lidia.developmenttest.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 - Add a method payFee to use for payment registration
 - Each student can pay a part of tuition fee or full tuition fee.
 - Each student that has paid a part of tuition fee can continue paying until he has paid full tuition fee
 (The student can not pay more than full tuition fee – 5000$).
 - Output the list of students and their paid fees ordered alphabetically into a text file (student.text) and save it on the local PC.
 */

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void payFee(int id, double amount) {
        Student student = studentRepository.findById(id);
        double feesPaid = student.getFeesPaid();
        if (feesPaid + amount <= 5000) {
            student.setFeesPaid(feesPaid + amount);
            studentRepository.save(student);
        } else {
           logger.error("You cannot pay more than full tuition fee.");
        }
    }

    public void writeToFile(List<Student> students) {
        try {
            FileWriter fileWriter = new FileWriter("student.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Students and their paid tuition fees");

            students.forEach(student -> {
                printWriter.printf("Student: %s, Paid fees: %.2f$", student.getName(), student.getFeesPaid());
                printWriter.println();
            });

            printWriter.close();
        } catch (IOException e) {
            logger.error("Could not write the student data to file.");
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudentsOrderedAlphabetically(int schoolId) {
        return studentRepository.findAllBySchoolIdOrderByNameAsc(schoolId);
    }

}
