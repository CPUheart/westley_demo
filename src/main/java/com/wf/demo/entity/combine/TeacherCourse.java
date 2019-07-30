package com.wf.demo.entity.combine;

public class TeacherCourse {
    private String id;          //教师编号
    private String name;        //教师姓名
    private String gender;      //教师性别
    private String courseName;  //教师教授课程名称

    public TeacherCourse(String id, String name, String gender, String courseName) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.courseName = courseName;
    }
    public TeacherCourse(){}

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
