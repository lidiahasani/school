package com.lidia.developmenttest.repository;

import com.lidia.developmenttest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findById(int id);

    List<Student> findAllBySchoolIdOrderByNameAsc(int id);

}
