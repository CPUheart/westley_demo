package com.wf.demo.dao;

import com.wf.demo.entity.Class;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ClassDao {
    int addClass(Class _class);

    int deleteClassById(Long id);

    int updateClass(Class _class);

    Class queryById(Long id);

    List<Class> queryAllClass();

    List<Class> queryByGrade(String grade);

    Class queryByGradeAndNumber(@Param("grade")String grade, @Param("classNumber") int classNumber);

}
