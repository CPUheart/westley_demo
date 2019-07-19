package com.wf.demo.entity;

public class Class {
    private Long id;
    private String grade;
    private String classNumber;

    public Class() {
    }

    public Class(Long id, String grade, String classNumber) {
        this.id = id;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", class_number='" + classNumber + '\'' +
                '}';
    }
}
