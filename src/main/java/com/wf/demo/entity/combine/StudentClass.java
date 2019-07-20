package com.wf.demo.entity.combine;

public class StudentClass {
    private String id;
    private String name;
    private String gender;
    private Long classId;
    private String grade;
    private String classNumber;

    public StudentClass() {
    }

    public StudentClass(String id, String name, String gender, Long classId, String grade, String classNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.classId = classId;
        this.grade = grade;
        this.classNumber = classNumber;
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

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
