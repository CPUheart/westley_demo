package com.wf.demo.service.impl;

import com.wf.demo.dao.ClassDao;
import com.wf.demo.entity.Class;
import com.wf.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassDao classDao;

    @Override
    public int addClass(Class _class) {
        return classDao.addClass(_class);
    }

    @Override
    public int deleteClassById(Long id) {
        return classDao.deleteClassById(id);
    }

    @Override
    public int updateClass(Class _class) {
        return classDao.updateClass(_class);
    }

    @Override
    public Class queryById(Long id) {
        return classDao.queryById(id);
    }

    @Override
    public List<Class> queryAllClass() {
        return classDao.queryAllClass();
    }

    @Override
    public List<Class> queryByGrade(String grade) {
        return classDao.queryByGrade(grade);
    }

    @Override
    public Class queryByGradeAndNumber(String grade, int classNumber) {
        return classDao.queryByGradeAndNumber(grade,classNumber);
    }
}
