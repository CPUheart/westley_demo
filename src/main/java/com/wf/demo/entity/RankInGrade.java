package com.wf.demo.entity;

import com.wf.demo.entity.combine.ScoreRank;

import java.util.List;

public class RankInGrade {
    private int courseId;
    private String grade;
    private List<ScoreRank> scoreRankList;

    public RankInGrade() {
    }

    public RankInGrade(int courseId, String grade, List<ScoreRank> scoreRankList) {
        this.courseId = courseId;
        this.grade = grade;
        this.scoreRankList = scoreRankList;
        int rank=1;
        for(ScoreRank scoreRank:scoreRankList) {
            scoreRank.setRank(rank);
            rank++;
        }
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
