package com.wf.demo.entity;

public class RankInClass {
    int courseId;
    int classId;
    int scoreNumber;

    public RankInClass() {
    }

    public RankInClass(int courseId, int classId, int scoreNumber) {
        this.courseId = courseId;
        this.classId = classId;
        this.scoreNumber = scoreNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    @Override
    public String toString() {
        return "RankInClass{" +
                "courseId=" + courseId +
                ", classId=" + classId +
                ", scoreNumber=" + scoreNumber +
                '}';
    }
}
