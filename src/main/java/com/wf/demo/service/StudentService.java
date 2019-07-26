package com.wf.demo.service;

import com.wf.demo.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> queryAllStudent();

    List<Student> queryByName(String name);

    int addStudent(Student student);

    int deleteStudent(String id);

    int updateStudent(Student student);

    Student queryById(String id);

    List<Student> queryByClassId(int id);

    int countByClassId(int id);
}
