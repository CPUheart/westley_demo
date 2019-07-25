package com.wf.demo.dao;

import com.wf.demo.entity.Course;

import java.util.List;

public interface CourseDaoR {
    List<Course> queryAllCourse();

    List<Course> queryAllOpenCourse();

    Course queryById(Long id);

    Course queryByName(String name);

    Course queryByTeacherId(String teacherId);

    int insertCourse(Course course);

    int updateCourse(Course course);

    int deleteByCourse(Long id);

    int closeCourse(Long id);
}
