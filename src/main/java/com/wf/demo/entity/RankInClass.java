package com.wf.demo.entity;

import com.wf.demo.entity.combine.ScoreRank;

import java.util.List;

public class RankInClass {
    private int courseId;       //课程id
    private int classId;        //班级id
    private List<ScoreRank> scoreRankList;

    public RankInClass() {
    }

    public RankInClass(int courseId, int classId, List<ScoreRank> scoreRankList) {
        this.courseId = courseId;
        this.classId = classId;
        this.scoreRankList = scoreRankList;
    }

    public List<ScoreRank> getScoreRankList() {
        return scoreRankList;
    }

    public void setScoreRankList(List<ScoreRank> scoreRankList) {
        this.scoreRankList = scoreRankList;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "RankInClass{" +
                "courseId=" + courseId +
                ", classId=" + classId +
                ", scoreRankList=" + scoreRankList +
                '}';
    }
}
