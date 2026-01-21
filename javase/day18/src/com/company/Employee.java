package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jh
 * @project com.company
 * @time 2026/1/21
 */
public class Employee {
    private String Id;
    private String name;
    private String gender;
    private int age;
    private String inDate;
    private int salary;
    private String workAddress;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public Employee(String Id, String name, String gender, int age, String inDate, int salary, String workAddress) {
        this.Id = Id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.inDate = inDate;
        this.salary = salary;
        this.workAddress = workAddress;
    }

    public String getInDate() {
        return inDate;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "工号：" + Id + "，姓名：" + name + "，入职时间：" + inDate + "，工资：" + salary;
    }


    protected static void sortEmployees(List<Employee> employees) {
        Collections.sort(employees,
                new Comparator<Employee>() {
                    @Override
                    public int compare(Employee e1, Employee e2) {
                        int dateCompare = e1.getInDate().compareTo(e2.getInDate());
                        if (dateCompare != 0) {
                            return dateCompare;
                        }
                        return Integer.compare(e2.getSalary(), e1.getSalary());
                    }
                });
    }
}


