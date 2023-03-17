package com.lidia.developmenttest.service;

import com.lidia.developmenttest.model.School;
import com.lidia.developmenttest.model.Student;
import com.lidia.developmenttest.model.Teacher;
import com.lidia.developmenttest.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 - Find total earning of school from tuition fees
 - Find total spending of school from teachers’ salary
 */

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public School findSchoolWithEarnings(int id) {
        School school = schoolRepository.findById(id);
        school.setTotalMoneyEarned(school.getStudents().stream().mapToDouble(Student::getFeesPaid).sum());
        school.setTotalMoneySpent(school.getTeachers().stream().mapToDouble(Teacher::getSalary).sum());
        return school;
    }

}
