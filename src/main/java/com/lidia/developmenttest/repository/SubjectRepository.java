package com.lidia.developmenttest.repository;

import com.lidia.developmenttest.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
