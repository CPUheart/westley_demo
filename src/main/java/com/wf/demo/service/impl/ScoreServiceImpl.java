package com.wf.demo.service.impl;

import com.sun.corba.se.spi.servicecontext.ServiceContextData;
import com.wf.demo.dao.ScoreDao;
import com.wf.demo.entity.Score;
import com.wf.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.spi.ServiceDelegate;
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
}
