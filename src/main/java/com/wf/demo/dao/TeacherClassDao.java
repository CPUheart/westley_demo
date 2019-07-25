package com.wf.demo.dao;

import com.wf.demo.entity.TeacherClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassDao {
    List<TeacherClass> queryAllTeacherClass();

    List<TeacherClass> queryAllNotAdvisor();

    List<TeacherClass> queryByTeacher(String teacherId);

    TeacherClass queryByAdvisor(String teacherId);

    List<TeacherClass> queryByClassId(Long classId);

    int addTeacherClass(TeacherClass teacherClass);

    int updateTeacherClass(TeacherClass teacherClass);

    int deleteTeacherClass(String teacherId, Long classId);

    int deleteAdvisorByClassId(Long classId);

    int deleteTeacherByClassId(Long classId);

    int setAdvisorByClassIdAndTeacherId(Long classId, String teacherId);


    TeacherClass queryAdvisorByClassId(Long classId);

    TeacherClass queryByTeacherAndClass(@Param("classId") Long classId, @Param("teacherId") String teacherId);
}
