package com.wf.demo.entity;

public class Course {
    private Long id;
    private String name;
    private int open;

    public Course(Long id, String name, int open) {
        this.id = id;
        this.name = name;
        this.open = open;
    }

    public Course(Long id, String name) {
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
