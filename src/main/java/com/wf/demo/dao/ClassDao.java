package com.wf.demo.dao;

import com.wf.demo.entity.Class;

import java.util.List;


public interface ClassDao {
    int addClass(Class _class);

    int deleteClassById(Long id);

    int updateClass(Class _class);

    Class queryById(Long id);

    List<Class> queryAllClass();
}
