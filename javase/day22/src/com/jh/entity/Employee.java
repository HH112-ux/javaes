package com.jh.entity;

import java.time.LocalDate;

/**
 * @author jh
 * @project com.jh.entity
 * @time 2026/1/30
 */
public class Employee {
    private String id;
    private String name;
    private String gender;
    private Integer age;
    private Integer salary;
    private String department;
    private LocalDate hireDate;

    public Employee(String id, String name, String gender, Integer age, Integer salary, String department, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.department = department;
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

    public Integer getAge() {
        return age;
    }

    public Integer getSalary() {
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
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}


