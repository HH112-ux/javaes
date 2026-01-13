package com.employee.management.entity;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public class Programmer extends Employee {

    public Programmer(String name, int basicSalary) {
        super(name, basicSalary);
    }

    @Override
    public int calculateSalary() {
        return basicSalary;
    }

    @Override
    public String getReason() {
        return "代码有bug";
    }
}
