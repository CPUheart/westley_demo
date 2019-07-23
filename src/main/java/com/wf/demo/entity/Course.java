package com.wf.demo.entity;

public class Course {
    private Long id;
    private String name;
    private String teacherId;

    public Course(Long id, String name, String teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacherId;
    }

    public void setTeacher(String teacherId) {
        this.teacherId = teacherId;
    }

}
