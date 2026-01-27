package com.jh.entity;

import java.time.LocalDate;

/**
 * @author jh
 * @project com.jh.entity
 * @time 2026/1/27
 */
public class Employee {

    private String id;
    private String name;
    private String gender;
    private int age;
    private double salary;
    private String department;
    private LocalDate hireDate;

    public Employee(String id, String name, String gender, int age, double salary, String department, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.department = department;
        this.hireDate = hireDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return "员工{编号：'" + id + "', 名字：'" + name + "', 性别：'" + gender + "', 年纪：" + age + ", 工质：" + salary + "部门：" + department + "', 入职时间：" + hireDate + '}';
    }
}


