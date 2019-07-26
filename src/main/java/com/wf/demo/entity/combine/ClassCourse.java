package com.wf.demo.entity.combine;

import com.wf.demo.entity.ClassInfo;
import com.wf.demo.entity.Course;

public class ClassCourse {
    private int classId;
    private String grade;
    private int classNumber;
    private int courseId;
    private String courseName;

    public ClassCourse() {
    }
    public ClassCourse(ClassInfo classInfo, Course course) {
        this.classId = classInfo.getId();
        this.grade = classInfo.getGrade();
        this.classNumber = classInfo.getClassNumber();
        this.courseId = course.getId();
        this.courseName = course.getName();
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
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

    public ClassCourse(int classId, String grade, int classNumber, int courseId, String courseName) {
        this.classId = classId;
        this.grade = grade;
        this.classNumber = classNumber;
        this.courseId = courseId;
        this.courseName = courseName;
    }
}
