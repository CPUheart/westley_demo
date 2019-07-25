package com.wf.demo.service.impl;

import com.wf.demo.dao.CourseListDao;
import com.wf.demo.entity.CourseList;
import com.wf.demo.service.CourseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseListServiceImpl implements CourseListService {

    @Autowired
    CourseListDao courseListDao;

    @Override
    public List<CourseList> queryAllCourseList() {
        return courseListDao.queryAllCourseList();
    }

    @Override
    public CourseList queryByName(String name) {
        return courseListDao.queryByName(name);
    }

    @Override
    public CourseList queryById(Long id) {
        return courseListDao.queryById(id);
    }

    @Override
    public int insertCourseList(CourseList courseList) {
        return courseListDao.insertCourseList(courseList);
    }

    @Override
    public int updateCourseList(CourseList courseList) {
        return 0;
    }

    @Override
    public int deleteCourseList(CourseList courseList) {
        return 0;
    }
}
