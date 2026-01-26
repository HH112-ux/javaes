package com.lambda.demo;

import com.lambda.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author jh
 * @project com.lambda.demo
 * @time 2026/1/27
 */
public class Test4 {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("李白", 22));
        studentList.add(new Student("杜甫", 18));
        studentList.add(new Student("王维", 25));
        System.out.println("排序前的学生集合：" + studentList);

        studentList.sort(Comparator.comparingInt(Student::getAge));
        System.out.println("按年龄升序排序后的学生集合：" + studentList);
    }

}
