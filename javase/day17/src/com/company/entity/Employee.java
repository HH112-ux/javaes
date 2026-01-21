package com.company.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author jh
 * @project com.company
 * @time 2026/1/21
 */
public class Employee {
    private String id;
    private String name;
    private int age;
    private BigDecimal salary;
    private String gender;
    private LocalDate inDate;
    private String workAddress;

    public Employee(String id, String name, String gender, int age, LocalDate inDate, BigDecimal salary, String workAddress) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.inDate = inDate;
        this.salary = salary;
        this.workAddress = workAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
         return "工号\t"+id+"\t姓名\t"+name+"\t性别\t"+gender+"\t年龄\t"+age+"\t入职时间\t"+inDate+"\t工资\t"+salary+"\t工作地点\t"+workAddress;
    }

}
