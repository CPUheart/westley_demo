package com.wf.demo.service;

import com.wf.demo.entity.Score;

import java.util.List;

public interface ScoreService {
    List<Score> queryAllScore();

    List<Score> queryAllNoneScore();

    Score queryByCourseAndStudent(int courseId, String studentId);

    int insertScore(Score score);

    int updateScore(Score score);

    int deleteScore(int courseId,String studentId);
}
