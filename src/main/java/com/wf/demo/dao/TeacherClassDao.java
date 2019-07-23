package com.wf.demo.dao;

import com.wf.demo.entity.TeacherClass;

import java.util.List;

public interface TeacherClassDao {
    List<TeacherClass> queryAllTeacherClass();

    List<TeacherClass> queryAllNotAdvisor();

    List<TeacherClass> queryByTeacher(String teacherId);

    TeacherClass queryByAdvisor(String teacherId);

    List<TeacherClass> queryByClassId(Long classId);

    int addTeacherClass(TeacherClass teacherClass);

    int updateTeacherClass(TeacherClass teacherClass);

    int deleteTeacherClass(String teacherId, Long classId);

    int deleteByClassId(Long classId);

    TeacherClass queryAdvisorByClassId(Long classId);
}
