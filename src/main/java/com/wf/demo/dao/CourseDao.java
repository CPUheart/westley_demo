package com.wf.demo.dao;

import com.wf.demo.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> queryAllCourse();

    Course queryById(Long id);

    Course queryByName(String name);

    int insertCourse(Course course);

    int updateCourse(Course course);

    int deleteByCourse(Long id);
}
