package com.wf.demo.service;

import com.wf.demo.entity.Score;
import com.wf.demo.entity.RankInClass;
import com.wf.demo.entity.RankInGrade;
import com.wf.demo.entity.combine.ScoreRank;

import java.util.List;

public interface ScoreService {
    List<Score> queryAllScore();

    List<Score> queryAllNoneScore();

    List<Score> queryByCourseAndClass(int courseId, int classId);

    Score queryByCourseAndStudent(int courseId, String studentId);

    int insertScore(Score score);

    int updateScore(Score score);

    int deleteScore(int courseId,String studentId);

    List<ScoreRank> getRankInClass(int courseId, int classId);

    List<ScoreRank> getRankInGrade(int courseId, String grade) ;

    List<Score> queryByCourseAndClassAndScore(int courseId, int classId, int scoreNumber);

    List<Score> queryByCourseAndGradeAndScore(int courseId, String grade, int scoreNumber);

    int setRankInClass(int courseId, int classId, int scoreNumber, int rank);

    int setRankInGrade(int courseId, String grade, int scoreNumber, int rank);
}
