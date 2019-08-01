package com.wf.demo.service;

import com.wf.demo.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> queryAllCourse();

    Course queryById(int id);

    Course queryByName(String name);


}
