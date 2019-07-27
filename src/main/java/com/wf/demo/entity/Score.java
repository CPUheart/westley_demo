package com.wf.demo.entity;

public class Score {
    private int courseId;
    private String studentId;
    private int scoreNumber;
    private int rankInClass;
    private int rankInGrade;

    public Score(int courseId, String studentId, int scoreNumber, int rankInClass, int rankInGrade) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.scoreNumber = scoreNumber;
        this.rankInClass = rankInClass;
        this.rankInGrade = rankInGrade;
    }

    public int getRankInClass() {
        return rankInClass;
    }

    public void setRankInClass(int rankInClass) {
        this.rankInClass = rankInClass;
    }

    public int getRankInGrade() {
        return rankInGrade;
    }

    public void setRankInGrade(int rankInGrade) {
        this.rankInGrade = rankInGrade;
    }

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

    @Override
    public String toString() {
        return "Score{" +
                "courseId=" + courseId +
                ", studentId='" + studentId + '\'' +
                ", scoreNumber=" + scoreNumber +
                '}';
    }
}
