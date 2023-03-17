package com.lidia.developmenttest.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a class School that should include the following information as instance variables:
 * students – type List<Student>
 * teachers – type List<Teacher>
 * total money earned – type double
 * total money spent – type double
 * create a method addTeacher that adds a Teacher in the list
 * create a method addStudent that adds a Student in the list
 */

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "school")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers = new ArrayList<>();

    @Transient
    private double totalMoneyEarned;

    @Transient
    private double totalMoneySpent;

    public void addTeacher(Teacher teacher) {
        teacher.setSchool(this);
        this.teachers.add(teacher);
    }

    public void addStudent(Student student) {
        student.setSchool(this);
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(double totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }

    public double getTotalMoneySpent() {
        return totalMoneySpent;
    }

    public void setTotalMoneySpent(double totalMoneySpent) {
        this.totalMoneySpent = totalMoneySpent;
    }
}
