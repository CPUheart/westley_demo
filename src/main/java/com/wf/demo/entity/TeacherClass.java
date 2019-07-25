package com.wf.demo.entity;

public class TeacherClass {
    private String teacherId;
    private Long classId;
    private Long courseId;
    private int advisor;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public TeacherClass() {
    }

    public TeacherClass(String teacherId, Long classId, Long courseId, int advisor) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseId=courseId;
        this.advisor = advisor;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public int getAdvisor() {
        return advisor;
    }

    public void setAdvisor(int advisor) {
        this.advisor = advisor;
    }

}
