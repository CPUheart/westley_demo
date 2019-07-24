package com.wf.demo.entity;

public class Score {
    private String courseId;
    private String studentId;
    private int score;
    private int rankInClass;
    private int rankInGrade;

    public Score(String courseId, String studentId, int score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
        this.rankInClass=0;
        this.rankInGrade=0;
    }

    public int getRankInGrade() {
        return rankInGrade;
    }

    public void setRankInGrade(int rankInGrade) {
        this.rankInGrade = rankInGrade;
    }

    public int getRankInClass() {
        return rankInClass;
    }

    public void setRankInClass(int rankInClass) {
        this.rankInClass = rankInClass;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
