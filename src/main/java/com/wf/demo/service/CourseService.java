package com.wf.demo.service;

import com.wf.demo.entity.Course;
import com.wf.demo.entity.CourseList;

import java.util.List;

public interface CourseService {
    List<Course> queryAllCourse();

    Course queryById(Long id);

    Course queryByName(String name);

    int insertCourse(Course course);

    int updateCourse(Course course);

    int deleteByCourse(Long id);

}
