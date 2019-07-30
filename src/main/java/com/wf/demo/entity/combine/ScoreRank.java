package com.wf.demo.entity.combine;

public class ScoreRank {
    private int scoreNumber;
    private int rank;

    public ScoreRank() {
    }

    public ScoreRank(int scoreNumber, int rank) {
        this.scoreNumber = scoreNumber;
        this.rank = rank;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "ScoreRank{" +
                "scoreNumber=" + scoreNumber +
                ", rank=" + rank +
                '}';
    }
}
