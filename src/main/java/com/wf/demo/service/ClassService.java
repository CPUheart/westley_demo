package com.wf.demo.service;

import com.wf.demo.entity.Class;

import java.util.List;

public interface ClassService {
    int addClass(Class _class);

    int deleteClassById(Long id);

    int updateClass(Class _class);

    Class queryById(Long id);

    List<Class> queryAllClass();

    List<Class> queryByGrade(String grade);

    Class queryByGradeAndNumber(String grade, int classNumber);

}
