package com.wf.demo.service.impl;

import com.wf.demo.dao.TeacherClassDao;
import com.wf.demo.dao.TeacherDao;
import com.wf.demo.entity.Teacher;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    TeacherClassDao teacherClassDao;

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

    @Override
    public List<Teacher> queryAllNotAdvisor() {
        List<Teacher> teachers = teacherDao.queryAllTeacher();
        List<Teacher> list = new ArrayList<>();
        for(Teacher teacher:teachers) {
            if(teacherClassDao.queryByAdvisor(teacher.getId())==null) {
                list.add(teacher);
            }
        }
        return list;
    }

    @Override
    public List<Teacher> queryByClass(Long classId) {
        List<TeacherClass> teacherClasses = teacherClassDao.queryByClassId(classId);
        List<Teacher> teachers = new ArrayList<>();
        for(TeacherClass teacherClass:teacherClasses) {
            teachers.add(teacherDao.queryById(teacherClass.getTeacherId()));
        }
        return teachers;
    }

    @Override
    public Teacher queryByLeadClass(Long classId) {
        return teacherDao.queryById(teacherClassDao.queryAdvisorByClassId(classId).getTeacherId());
    }
}
