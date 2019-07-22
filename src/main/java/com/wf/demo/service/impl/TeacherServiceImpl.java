package com.wf.demo.service.impl;

import com.wf.demo.dao.TeacherDao;
import com.wf.demo.entity.Teacher;
import com.wf.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public int deleteTeacherById(String id) {
        return teacherDao.deleteTeacher(id);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherDao.updateTeacher(teacher);
    }

    @Override
    public Teacher queryById(String id) {
        return teacherDao.queryById(id);
    }

    @Override
    public List<Teacher> queryAllTeacher() {
        return teacherDao.queryAllTeacher();
    }

    @Override
    public List<Teacher> queryByName(String name) { return teacherDao.queryByName(name); }
}
