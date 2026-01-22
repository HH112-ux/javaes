package com.grademanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jh
 * @project com.grademanagement
 * @time 2026/1/22
 */
public class GradeStatistics {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Map<String, Double> liBaiScores = new HashMap<>();
        liBaiScores.put("语文", 98.0);
        liBaiScores.put("数学", 70.0);
        liBaiScores.put("英语", 60.0);
        liBaiScores.put("地理", 95.0);
        students.add(new Student("李白", liBaiScores));

        Map<String, Double> duFuScores = new HashMap<>();
        duFuScores.put("语文", 98.0);
        duFuScores.put("数学", 80.0);
        duFuScores.put("英语", 85.0);
        duFuScores.put("地理", 90.0);
        students.add(new Student("杜甫", duFuScores));

        Map<String, Double> baiJuYiScores = new HashMap<>();
        baiJuYiScores.put("语文", 95.0);
        baiJuYiScores.put("数学", 85.0);
        baiJuYiScores.put("英语", 55.0);
        baiJuYiScores.put("地理", 80.0);
        students.add(new Student("白居易", baiJuYiScores));

        Map<String, Double> liShangYinScores = new HashMap<>();
        liShangYinScores.put("语文", 90.0);
        liShangYinScores.put("数学", 50.0);
        liShangYinScores.put("英语", 58.0);
        liShangYinScores.put("地理", 70.0);
        students.add(new Student("李商隐", liShangYinScores));

        System.out.println("-------------------------");
        students.forEach(student -> {
            System.out.println(student.getName() + "：总成绩=" + student.getTotalScore() + "，平均成绩=" + student.getAverageScore());
        });

        System.out.println("--------------------------");
        Map<String, Double> subjectTotal = new HashMap<>();
        Map<String, Integer> subjectCount = new HashMap<>();
        students.forEach(student -> {
            student.getScores().forEach((subject, score) -> {
                subjectTotal.put(subject, subjectTotal.getOrDefault(subject, 0.0) + score);
                subjectCount.put(subject, subjectCount.getOrDefault(subject, 0) + 1);
            });
        });
        subjectTotal.forEach((subject, total) -> {
            double avg = total / subjectCount.get(subject);
            System.out.println(subject + "：总成绩=" + total + "，平均成绩=" + avg);
        });

        System.out.println("---------------------------");
        long failedCount = students.stream().filter(Student::hasFailed).count();
        System.out.println("原始挂科人数：" + failedCount);

        System.out.println("----------------------------");
        students.forEach(Student::boostFailedScores);
        long newFailedCount = students.stream().filter(Student::hasFailed).count();
        System.out.println("提升后挂科人数：" + newFailedCount);

        System.out.println("----------------------------");
        students.forEach(student -> {
            System.out.println(student.getName() + "：" + student.getScores());
        });
    }
}

