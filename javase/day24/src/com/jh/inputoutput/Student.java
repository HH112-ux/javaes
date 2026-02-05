package com.jh.inputoutput;

import java.io.Serializable;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String  gender;
    private String address;
    private  transient Teacher teacher;
    private String dept;

    public Student() {
    }

    public Student(String name, int age, String gender, String address,String dept ,Teacher teacher) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.teacher = teacher;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", teacher=" + teacher +
                ", dept='" + dept + '\'' +
                '}';
    }
}
