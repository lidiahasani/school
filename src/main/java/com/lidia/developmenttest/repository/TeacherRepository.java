package com.lidia.developmenttest.repository;

import com.lidia.developmenttest.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
