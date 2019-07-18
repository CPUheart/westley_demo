package com.wf.demo.entity;

public class TeacherClass {
    private String teacherId;
    private Long classId;
    private Long advisor;

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

    public Long getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Long advisor) {
        this.advisor = advisor;
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
                "teacherId='" + teacherId + '\'' +
                ", classId=" + classId +
                ", advisor=" + advisor +
                '}';
    }
}
