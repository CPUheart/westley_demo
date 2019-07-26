package com.wf.demo.dao;

import com.wf.demo.entity.TeacherClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassDao {
    List<TeacherClass> queryAllTeacherClass();

    List<TeacherClass> queryAllNotAdvisor();

    List<TeacherClass> queryByTeacher(String teacherId);

    TeacherClass queryByAdvisor(String teacherId);

    List<TeacherClass> queryByClassId(int classId);

    int addTeacherClass(TeacherClass teacherClass);

    int updateTeacherClass(TeacherClass teacherClass);

    int deleteTeacherClass(String teacherId, int classId);

    int deleteAdvisorByClassId(int classId);

    int deleteTeacherByClassId(int classId);

//    int setAdvisorByClassIdAndTeacherId(int classId, String teacherId);


    TeacherClass queryAdvisorByClassId(int classId);

    TeacherClass queryByClassAndCourse(@Param("classId") int classId, @Param("courseId") int courseId);

    TeacherClass queryByTeacherAndClass(@Param("classId") int classId, @Param("teacherId") String teacherId);
}
