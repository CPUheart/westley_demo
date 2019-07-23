package com.wf.demo.entity;

public class Student {
    private String id;
    private String name;
    private String gender;
    private Long classId;

    public Student() {
    }

    public Student(String id, String name, String gender, Long classId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.classId = classId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

}
