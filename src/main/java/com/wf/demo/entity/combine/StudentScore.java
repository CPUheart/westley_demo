package com.wf.demo.entity.combine;

public class StudentScore {
    String studentId;
    String studentName;
    int courseId;
    String courseName;
    int score;
    int rankInClass;
    int rankInGrade;

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

    public StudentScore(String studentId, String studentName, int courseId, String courseName, int score, int rankInClass, int rankInGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.score = score;
        this.rankInClass = rankInClass;
        this.rankInGrade = rankInGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
