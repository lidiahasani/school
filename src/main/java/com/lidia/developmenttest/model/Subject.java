package com.lidia.developmenttest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a class called Subject to represent a subject of school.
 * A Subject should have the following information as instance variables: * id – unique – type int
 * name – type String
 * teacher – type Teacher
 * student list - List<Student>
 */

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(mappedBy = "subject", cascade = CascadeType.PERSIST)
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();

    public Subject() {
    }

    public Subject(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
