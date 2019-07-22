package com.wf.demo.dao;

import com.wf.demo.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> queryAllTeacher();

    Teacher queryById(String id);

    List<Teacher>   queryByName(String name);

    int addTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    int deleteTeacher(String id);
}
