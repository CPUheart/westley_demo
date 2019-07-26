package com.wf.demo.dao;

import com.wf.demo.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    List<Course> queryAllCourse();

    Course queryById(int id);

    Course queryByName(String name);

    int insertCourse(Course course);

    int updateCourse(Course course);

    int deleteByCourse(int id);
}
