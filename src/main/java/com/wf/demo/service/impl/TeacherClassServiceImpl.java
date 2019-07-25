package com.wf.demo.service.impl;

import com.wf.demo.dao.TeacherClassDao;
import com.wf.demo.entity.TeacherClass;
import com.wf.demo.service.TeacherClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherClassServiceImpl implements TeacherClassService {
    @Autowired
    TeacherClassDao teacherClassDao;

    @Override
    public List<TeacherClass> queryAllTeacherClass() {
        return teacherClassDao.queryAllTeacherClass();
    }

    @Override
    public List<TeacherClass> queryByTeacher(String teacherId) {
        return teacherClassDao.queryByTeacher(teacherId);
    }

    @Override
    public TeacherClass queryByAdvisor(String teacherId) {
        return teacherClassDao.queryByAdvisor(teacherId);
    }

    @Override
    public TeacherClass queryByTeacherAndClass(Long classId, String teacherId) {
        return teacherClassDao.queryByTeacherAndClass(classId,teacherId);
    }

    @Override
    public List<TeacherClass> queryByClassId(Long classId) {
        return teacherClassDao.queryByClassId(classId);
    }

    @Override
    public int addTeacherClass(TeacherClass teacherClass) {
        return teacherClassDao.addTeacherClass(teacherClass);
    }

    @Override
    public int updateTeacherClass(TeacherClass teacherClass) {
        return teacherClassDao.updateTeacherClass(teacherClass);
    }

    @Override
    public int deleteTeacherClass(String teacherId, Long classId) {
        return teacherClassDao.deleteTeacherClass(teacherId,classId);
    }

    @Override
    public int deleteAdvisorByClassId(Long classId) {
        return teacherClassDao.deleteAdvisorByClassId(classId);
    }

    @Override
    public int deleteTeacherByClassId(Long classId) {
        return teacherClassDao.deleteTeacherByClassId(classId);
    }

    @Override
    public TeacherClass queryAdvisorByClassId(Long classId) {
        return teacherClassDao.queryAdvisorByClassId(classId);
    }

    @Override
    public int setAdvisorByClassIdAndTeacherId(Long classId, String teacherId) {
        return teacherClassDao.setAdvisorByClassIdAndTeacherId(classId,teacherId);
    }
}
