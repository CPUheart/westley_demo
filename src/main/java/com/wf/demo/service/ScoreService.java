package com.wf.demo.service;

import com.wf.demo.entity.Score;
import com.wf.demo.entity.RankInClass;
import com.wf.demo.entity.RankInGrade;

import java.util.List;

public interface ScoreService {
    List<Score> queryAllScore();

    List<Score> queryAllNoneScore();

    List<Score> queryByCourseAndClass(int courseId, int classId);

    Score queryByCourseAndStudent(int courseId, String studentId);

    int insertScore(Score score);

    int updateScore(Score score);

//    int deleteScore(int courseId,String studentId);

    List<RankInClass> getRankInClass(int courseId, int classId);

    List<RankInGrade> getRankInGrade(int courseId, String grade) ;

    List<Score> queryByCourseAndClassAndScore(int courseId, int classId, int scoreNumber);

    List<Score> queryByCourseAndGradeAndScore(int courseId, String grade, int scoreNumber);

}
