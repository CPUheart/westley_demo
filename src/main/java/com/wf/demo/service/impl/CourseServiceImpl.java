package com.wf.demo.service.impl;

import com.wf.demo.dao.CourseDao;
import com.wf.demo.entity.Course;
import com.wf.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Override
    public List<Course> queryAllCourse() {
        return courseDao.queryAllCourse();
    }

    @Override
    public Course queryById(int id) {
        return courseDao.queryById(id);
    }

    @Override
    public Course queryByName(String name) {
        return courseDao.queryByName(name);
    }

    @Override
    public int insertCourse(Course course) {
        return courseDao.insertCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public int deleteByCourse(int id) {
        return courseDao.deleteByCourse(id);
    }
}
