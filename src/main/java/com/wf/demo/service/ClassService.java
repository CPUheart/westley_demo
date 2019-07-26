package com.wf.demo.service;

import com.wf.demo.entity.ClassInfo;

import java.util.List;

public interface ClassService {
    int addClass(ClassInfo classInfo);

    int deleteClassById(int id);

    int updateClass(ClassInfo classInfo);

    ClassInfo queryById(int id);

    List<ClassInfo> queryAllClass();

    List<ClassInfo> queryByGrade(String grade);

    ClassInfo queryByGradeAndNumber(String grade, int classNumber);

    List<String> queryAllGrade();

    Integer[] queryAllClassNumber();

}
