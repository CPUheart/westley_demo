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
    public TeacherClass queryByTeacherAndClass(int classId, String teacherId) {
        return teacherClassDao.queryByTeacherAndClass(classId,teacherId);
    }

    @Override
    public List<TeacherClass> queryByClassId(int classId) {
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
    public int deleteTeacherClass(String teacherId, int classId) {
        return teacherClassDao.deleteTeacherClass(teacherId,classId);
    }

    @Override
    public int deleteAdvisorByClassId(int classId) {
        return teacherClassDao.deleteAdvisorByClassId(classId);
    }

    @Override
    public int deleteTeacherByClassId(int classId) {
        return teacherClassDao.deleteTeacherByClassId(classId);
    }

    @Override
    public TeacherClass queryAdvisorByClassId(int classId) {
        return teacherClassDao.queryAdvisorByClassId(classId);
    }

    @Override
    public int setAdvisorByClassAndTeacher(int classId, String teacherId) {
        TeacherClass teacherClass = teacherClassDao.queryByTeacherAndClass(classId,teacherId);
        teacherClass.setAdvisor(1);
        return teacherClassDao.updateTeacherClass(teacherClass);
    }

    @Override
    public int updateAdvisorByClassAndTeacher(int classId, String teacherId) {
        List<TeacherClass> teacherClasses = teacherClassDao.queryByClassId(classId);
        for(TeacherClass teacherClass:teacherClasses) {
            teacherClass.setAdvisor(0);
            teacherClassDao.updateTeacherClass(teacherClass);
        }
        this.setAdvisorByClassAndTeacher(classId,teacherId);
        return 1;
    }

    @Override
    public TeacherClass queryByClassAndCourse(int classId, int courseId) {
        return teacherClassDao.queryByClassAndCourse(classId,courseId);
    }


}
