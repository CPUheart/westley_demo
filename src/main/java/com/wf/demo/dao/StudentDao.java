package com.wf.demo.dao;

import com.wf.demo.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> queryAllStudent();

    List<Student> queryByName(String name);

    Student queryById(String id);

    int addStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(String id);

    List<Student> queryByClassId(Long classId);

    int countByClassId(Long classId);
}
