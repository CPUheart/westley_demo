package com.wf.demo.dao;

import com.wf.demo.entity.Score;
import com.wf.demo.entity.RankInClass;
import com.wf.demo.entity.RankInGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreDao {
    List<Score> queryAllScore();

    List<Score> queryAllNoneScore();

    Score queryByCourseAndStudent(@Param("courseId")int courseId, @Param("studentId")String studentId);

    int insertScore(Score score);

    int updateScore(Score score);

    int deleteScore(@Param("courseId") int courseId,@Param("studentId") String studentId);

    List<RankInClass> getRankInClass(@Param("courseId")int courseId, @Param("classId")int classId);

    List<RankInGrade> getRankInGrade(@Param("courseId")int courseId, @Param("grade")String grade);

    List<Score> queryByCourseAndClass(@Param("courseId") int courseId,@Param("classId") int classId);

    List<Score> queryByCourseAndClassAndScore(@Param("courseId")int courseId, @Param("classId")int classId, @Param("scoreNumber")int scoreNumber);

    List<Score> queryByCourseAndGradeAndScore(@Param("courseId")int courseId, @Param("grade")String grade, @Param("scoreNumber")int scoreNumber);

}
