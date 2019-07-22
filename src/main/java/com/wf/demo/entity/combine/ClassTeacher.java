package com.wf.demo.entity.combine;

public class ClassTeacher {
    private Long classId;
    private String grade;
    private int classNumber;
    private String advisorId;
    private String name;
    private String gender;

    public ClassTeacher() {
    }

    public ClassTeacher(Long classId, String grade, int classNumber, String advisorId, String name, String gender) {
        this.classId = classId;
        this.grade = grade;
        this.classNumber = classNumber;
        this.advisorId = advisorId;
        this.name = name;
        this.gender = gender;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
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

    @Override
    public String toString() {
        return "ClassTeacher{" +
                "grade='" + grade + '\'' +
                ", classNumber=" + classNumber +
                ", advisorId='" + advisorId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
