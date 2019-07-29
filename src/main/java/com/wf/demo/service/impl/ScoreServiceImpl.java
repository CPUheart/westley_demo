package com.wf.demo.service.impl;

import com.wf.demo.dao.ScoreDao;
import com.wf.demo.entity.Score;
import com.wf.demo.entity.RankInClass;
import com.wf.demo.entity.RankInGrade;
import com.wf.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;

    @Override
    public List<Score> queryAllScore() {
        return scoreDao.queryAllScore();
    }

    @Override
    public List<Score> queryAllNoneScore() {
        return scoreDao.queryAllNoneScore();
    }

    @Override
    public List<Score> queryByCourseAndClass(int courseId, int classId) {
        return scoreDao.queryByCourseAndClass(courseId,classId);
    }

    @Override
    public Score queryByCourseAndStudent(int courseId, String studentId) {
        return scoreDao.queryByCourseAndStudent(courseId, studentId);
    }

    @Override
    public int insertScore(Score score) {
        return scoreDao.insertScore(score);
    }

    @Override
    public int updateScore(Score score) {
        return scoreDao.updateScore(score);
    }

    @Override
    public int deleteScore(int courseId, String studentId) {
        return scoreDao.deleteScore(courseId,studentId);
    }

    @Override
    public List<RankInClass> getRankInClass(int courseId, int classId) {
        return scoreDao.getRankInClass(courseId,classId);
    }

    @Override
    public List<RankInGrade> getRankInGrade(int courseId, String grade) {
        return scoreDao.getRankInGrade(courseId, grade);
    }

    @Override
    public List<Score> queryByCourseAndClassAndScore(int courseId, int classId, int scoreNumber) {
        return scoreDao.queryByCourseAndClassAndScore(courseId,classId,scoreNumber);
    }

    @Override
    public List<Score> queryByCourseAndGradeAndScore(int courseId, String grade, int scoreNumber) {
        return scoreDao.queryByCourseAndGradeAndScore(courseId,grade,scoreNumber);
    }
}
