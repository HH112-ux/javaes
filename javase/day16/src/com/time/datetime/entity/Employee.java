package com.time.datetime.entity;

import com.time.datetime.util.DateUtil;

import java.time.LocalDate;

/**
 * @author jh
 * @project com.time.datetime.entity
 * @time 2026/1/19
 */
public class Employee {
    private String empName;
    private LocalDate inDate;
    private double salary;

    public Employee(String empName, LocalDate inDate, double salary) {
        this.empName = empName;
        this.inDate = inDate;
        this.salary = salary;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getHireMonths() {
        LocalDate now = LocalDate.now();
        return DateUtil.calculateBetween(inDate, now, "month");
    }

    public boolean isEligibleForRaise() {
        return getHireMonths() > 100;
    }

    public void raiseSalary() {
        if (isEligibleForRaise()) {
            this.salary += 1000;
        }
    }

    @Override
    public String toString() {
        return "Employee" + empName + inDate + "入职" + "工资" + salary + "入职月数" + getHireMonths();
    }


}
