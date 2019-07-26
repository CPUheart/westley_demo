package com.wf.demo.dao;

import com.wf.demo.entity.Score;
import org.apache.ibatis.annotations.Param;

import javax.annotation.PreDestroy;
import java.util.List;

public interface ScoreDao {
    List<Score> queryAllScore();

    Score queryByCourseAndStudent(@Param("courseId")int courseId, @Param("studentId")String studentId);

    int insertScore(Score score);

    int updateScore(Score score);

    int deleteScore(@Param("courseId") int courseId,@Param("studentId") String studentId);
}
