package com.grademanagement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * @author jh
 * @project com.grademanagement
 * @time 2026/1/22
 */
public class Student {

    private String name;
    private Map<String, Double> scores;

    public Student(String name, Map<String, Double> scores) {
        this.name = name;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    public double getTotalScore() {
        return scores.values().stream().mapToDouble(Double::doubleValue).sum();
    }


    public double getAverageScore() {
        return getTotalScore() / scores.size();
    }

    public void boostFailedScores() {
        scores.replaceAll((subject, score) -> {
            if (score < 60) {
                BigDecimal bd = new BigDecimal(score);
                bd = bd.multiply(new BigDecimal("1.1")).setScale(1, RoundingMode.HALF_UP);
                return bd.doubleValue();
            }
            return score;
        });
    }


    public boolean hasFailed() {
        return scores.values().stream().anyMatch(score -> score < 60);
    }
}




