package com.wf.demo.entity;

public class TeacherClass {
    private String teacherId;
    private int classId;
    private int courseId;
    private int advisor;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public TeacherClass() {
    }

    public TeacherClass(String teacherId, int classId, int courseId, int advisor) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseId = courseId;
        this.advisor = advisor;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getAdvisor() {
        return advisor;
    }

    public void setAdvisor(int advisor) {
        this.advisor = advisor;
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
                "teacherId='" + teacherId + '\'' +
                ", classId=" + classId +
                ", courseId=" + courseId +
                ", advisor=" + advisor +
                '}';
    }
}
