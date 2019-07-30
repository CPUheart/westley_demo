package com.wf.demo.entity.combine;

public class TeacherAdvisor {
    private String id;          //教师编号
    private String name;        //教师姓名
    private String gender;      //教师性别
    private int advisor;        //是否为班主任，1代表是班主任，0代表不是班主任
    private int classId;        //班级id
    private String grade;       //年级
    private int classNumber;    //班级

    public TeacherAdvisor(String id, String name, String gender, int advisor, int classId, String grade, int classNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.advisor = advisor;
        this.grade = grade;
        this.classNumber = classNumber;
        this.classId = classId;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
