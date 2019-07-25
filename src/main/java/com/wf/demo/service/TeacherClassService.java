package com.wf.demo.service;

import com.wf.demo.entity.TeacherClass;

import java.util.List;

public interface TeacherClassService {
    List<TeacherClass> queryAllTeacherClass();

    List<TeacherClass> queryByTeacher(String teacherId);

    TeacherClass queryByAdvisor(String teacherId);

    TeacherClass queryByTeacherAndClass(Long classId, String teacherId);

    List<TeacherClass> queryByClassId(Long classId);

    int addTeacherClass(TeacherClass teacherClass);

    int updateTeacherClass(TeacherClass teacherClass);

    int deleteTeacherClass(String teacherId, Long classId);

    int deleteAdvisorByClassId(Long classId);

    int deleteTeacherByClassId(Long classId);

    TeacherClass queryAdvisorByClassId(Long classId);

    int setAdvisorByClassIdAndTeacherId(Long classId, String teacherId);

}
