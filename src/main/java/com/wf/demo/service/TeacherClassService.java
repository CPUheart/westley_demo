package com.wf.demo.service;

import com.wf.demo.entity.TeacherClass;

import java.util.List;

public interface TeacherClassService {
    List<TeacherClass> queryAllTeacherClass();

    List<TeacherClass> queryByTeacher(String teacherId);

    TeacherClass queryByAdvisor(String teacherId);

    TeacherClass queryByTeacherAndClass(int classId, String teacherId);

    List<TeacherClass> queryByClassId(int classId);

    int addTeacherClass(TeacherClass teacherClass);

    int updateTeacherClass(TeacherClass teacherClass);

    int deleteTeacherClass(String teacherId, int classId);

    int deleteAdvisorByClassId(int classId);

    int deleteTeacherByClassId(int classId);

    TeacherClass queryAdvisorByClassId(int classId);

    int setAdvisorByClassAndTeacher(int classId, String teacherId);

    TeacherClass queryByClassAndCourse(int classId, int courseId);

    int updateAdvisorByClassAndTeacher(int classId, String teacherId);
}
