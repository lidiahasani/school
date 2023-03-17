package com.lidia.developmenttest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a class called Student to represent a student of school.
 * A Student should have the following information as instance variables: * id – unique – type int
 * name – type String
 * fees paid – type double
 */

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double feesPaid;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_school")
    private School school;

    @ManyToMany
    @JoinTable
    private List<Subject> subjects = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
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

    public double getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(double feesPaid) {
        this.feesPaid = feesPaid;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
