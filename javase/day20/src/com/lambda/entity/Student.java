package com.lambda.entity;

/**
 * @author jh
 * @project com.lambda
 * @time 2026/1/26
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{名字：" + name + ", 年龄：" + age + "}";
    }
}


