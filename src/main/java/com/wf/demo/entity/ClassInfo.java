package com.wf.demo.entity;

public class ClassInfo {
    private int id;
    private String grade;
    private int classNumber;

    public ClassInfo() {
    }

    public ClassInfo(int id, String grade, int classNumber) {
        this.id = id;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

}
