package com.wf.demo.service.impl;

import com.wf.demo.dao.ClassDao;
import com.wf.demo.entity.ClassInfo;
import com.wf.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassDao classDao;

    @Override
    public int addClass(ClassInfo classInfo) {
        return classDao.addClass(classInfo);
    }

    @Override
    public int deleteClassById(int id) {
        return classDao.deleteClassById(id);
    }

    @Override
    public int updateClass(ClassInfo classInfo) {
        return classDao.updateClass(classInfo);
    }

    @Override
    public ClassInfo queryById(int id) {
        return classDao.queryById(id);
    }

    @Override
    public List<ClassInfo> queryAllClass() {
        return classDao.queryAllClass();
    }

    @Override
    public List<ClassInfo> queryByGrade(String grade) {
        return classDao.queryByGrade(grade);
    }

    @Override
    public ClassInfo queryByGradeAndNumber(String grade, int classNumber) {
        return classDao.queryByGradeAndNumber(grade,classNumber);
    }

    @Override
    public List<String> queryAllGrade() {
        return classDao.queryAllGrade();
    }

    @Override
    public Integer[] queryAllClassNumber() {
        return classDao.queryAllClassNumber();
    }
}
