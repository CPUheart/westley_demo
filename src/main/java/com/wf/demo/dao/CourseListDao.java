package com.wf.demo.dao;

import com.wf.demo.entity.CourseList;

import java.util.List;

public interface CourseListDao {
    List<CourseList> queryAllCourseList();

    CourseList queryByName(String name);

    CourseList queryById(Long id);

    int insertCourseList(CourseList courseList);

    int updateCourseList(CourseList courseList);

    int deleteCourseList(CourseList courseList);
}
