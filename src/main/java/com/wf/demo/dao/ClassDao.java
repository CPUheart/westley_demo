package com.wf.demo.dao;

import com.wf.demo.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ClassDao {
    int addClass(ClassInfo classInfo);

    int deleteClassById(int id);

    int updateClass(ClassInfo classInfo);

    ClassInfo queryById(int id);

    List<ClassInfo> queryAllClass();

    List<ClassInfo> queryByGrade(String grade);

    ClassInfo queryByGradeAndNumber(@Param("grade")String grade, @Param("classNumber") int classNumber);

    List<String> queryAllGrade();

    Integer[] queryAllClassNumber();

}
