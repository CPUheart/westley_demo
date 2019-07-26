package com.wf.demo.entity.combine;

public class CourseR {
    private Long id;
    private String name;
    private int open;
    private String teacherId;

    public CourseR() {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public CourseR(Long id, String name, int open, String teacherId) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.teacherId=teacherId;
    }

    public CourseR(Long id, String name) {
        this.id = id;
        this.name = name;
        this.open=1;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
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
}
