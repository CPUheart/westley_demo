package com.wf.demo.entity.combine;

public class StudentScore {
    String studentId;       //学生id
    String studentName;     //学生姓名
    int courseId;           //课程id
    String courseName;      //课程名
    int scoreNumber;        //得分
    int rankInClass;        //班级排名
    int rankInGrade;        //年级排名

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

    public StudentScore(String studentId, String studentName, int courseId, String courseName, int scoreNumber, int rankInClass, int rankInGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.scoreNumber = scoreNumber;
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

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", scoreNumber=" + scoreNumber +
                ", rankInClass=" + rankInClass +
                ", rankInGrade=" + rankInGrade +
                '}';
    }
}
