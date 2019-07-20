package com.wf.demo.dao;

import com.wf.demo.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> queryAllStudent();

    Student queryById(String id);

    int addStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(String id);
}