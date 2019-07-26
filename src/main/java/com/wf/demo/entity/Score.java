package com.wf.demo.entity;

public class Score {
    private int courseId;
    private String studentId;
    private int scoreNumber;

    public Score() {
    }

    public Score(int courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.scoreNumber=-1;  //代表暂无考试成绩
    }

    public Score(int courseId, String studentId, int scoreNumber) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.scoreNumber = scoreNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

}
