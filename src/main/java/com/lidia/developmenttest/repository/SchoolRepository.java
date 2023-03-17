package com.lidia.developmenttest.repository;

import com.lidia.developmenttest.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {

    School findById(int id);

}
