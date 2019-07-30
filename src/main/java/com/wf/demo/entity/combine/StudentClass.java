package com.wf.demo.entity.combine;

public class StudentClass {
    private String id;          //学生id
    private String name;        //学生姓名
    private String gender;      //学生性别
    private int classId;        //学生所在班级id
    private String grade;       //学生所在年级
    private int classNumber;    //学生所在班级号

    public StudentClass() {
    }

    public StudentClass(String id, String name, String gender, String grade, int classNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public StudentClass(String id, String name, String gender, int classId, String grade, int classNumber) {
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
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
}
