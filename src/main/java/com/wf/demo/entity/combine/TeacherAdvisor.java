package com.wf.demo.entity.combine;

public class TeacherAdvisor {
    private String id;
    private String name;
    private String gender;
    private int advisor;
    private String grade;
    private int classNumber;

    public TeacherAdvisor(String id, String name, String gender, int advisor, String grade, int classNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.advisor = advisor;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAdvisor() {
        return advisor;
    }

    public void setAdvisor(int advisor) {
        this.advisor = advisor;
    }
}
