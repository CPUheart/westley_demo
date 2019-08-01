package com.wf.demo.service;

import com.wf.demo.entity.Student;

import java.util.List;

public interface StudentService {

    //查询所有学生
    List<Student> queryAllStudent();

    //通过姓名查询学生
    List<Student> queryByName(String name);

    //添加学生
    int addStudent(Student student);

    //通过id删除学生
    int deleteStudent(String id);

    //更新学生信息
    int updateStudent(Student student);

    //通过学生编号查询学生
    Student queryById(String id);

    //通过班级编号查询该班级的学生列表
    List<Student> queryByClassId(int id);

    //通过班级编号计算该班级学生数量
    int countByClassId(int id);
}
