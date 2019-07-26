package com.wf.demo.service.impl;

import com.wf.demo.dao.StudentDao;
import com.wf.demo.entity.Student;
import com.wf.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> queryAllStudent() {
        return studentDao.queryAllStudent();
    }

    @Override
    public List<Student> queryByName(String name) { return studentDao.queryByName(name); }

    @Override
    public int addStudent(Student student) {

        return studentDao.addStudent(student);
    }

    @Override
    public int deleteStudent(String id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public Student queryById(String id) {
        return studentDao.queryById(id);
    }

    @Override
    public List<Student> queryByClassId(int id) { return studentDao.queryByClassId(id); }

    @Override
    public int countByClassId(int id) { return studentDao.countByClassId(id); }

}
