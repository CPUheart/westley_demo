package com.wf.demo.entity;

public class RankInGrade {
    int courseId;
    String grade;
    int scoreNumber;

    @Override
    public String toString() {
        return "RankInGrade{" +
                "courseId=" + courseId +
                ", grade='" + grade + '\'' +
                ", scoreNumber=" + scoreNumber +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public RankInGrade(int courseId, String grade, int scoreNumber) {
        this.courseId = courseId;
        this.grade = grade;
        this.scoreNumber = scoreNumber;
    }

    public RankInGrade() {
    }
}
