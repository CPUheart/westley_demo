package com.wf.demo.service;

import com.wf.demo.entity.TeacherClass;

import java.util.List;

public interface TeacherClassService {
    List<TeacherClass> queryAllTeacherClass();

    List<TeacherClass> queryByTeacher(String teacherId);

    TeacherClass queryByAdvisor(String teacherId);

    List<TeacherClass> queryByClassId(Long classId);

    int addTeacherClass(TeacherClass teacherClass);

    int updateTeacherClass(TeacherClass teacherClass);

    int deleteTeacherClass(String teacherId, Long classId);

    int deleteByClassId(Long classId);

    TeacherClass queryAdvisorByClassId(Long classId);

}
