package com.wf.demo.service;

import com.wf.demo.entity.Teacher;

import java.util.List;

public interface TeacherService {
    //添加老师
    int addTeacher(Teacher teacher);

    //通过老师编号删除老师
    int deleteTeacherById(String id);

    //更新老师信息
    int updateTeacher(Teacher teacher);

    //通过老师编号查询
    Teacher queryById(String id);

    //查询所有老师
    List<Teacher> queryAllTeacher();

    //通过姓名查询老师
    List<Teacher> queryByName(String name);

    //查询所有没有担任班主任的老师列表
    List<Teacher> queryAllNotAdvisor();

    //通过班级编号查询该班的任课老师列表
    List<Teacher> queryByClass(int classId);

    //通过班级编号查询该班班主任
    Teacher queryByLeadClass(int classId);
}
