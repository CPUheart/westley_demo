package com.wf.demo.service;

import com.wf.demo.entity.Teacher;

import java.util.List;

public interface TeacherService {
    int addTeacher(Teacher teacher);

    int deleteTeacherById(String id);

    int updateTeacher(Teacher teacher);

    Teacher queryById(String id);

    List<Teacher> queryAllTeacher();

    List<Teacher> queryByName(String name);

    List<Teacher> queryAllNotAdvisor();

    Teacher queryByLeadClass(Long classId);
}
