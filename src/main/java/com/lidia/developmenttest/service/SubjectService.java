package com.lidia.developmenttest.service;

import com.lidia.developmenttest.model.Subject;
import com.lidia.developmenttest.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public void saveAll(List<Subject> subjects) {
        subjectRepository.saveAll(subjects);
    }

}
