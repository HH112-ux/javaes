package com.employee.management.service;

import com.employee.management.entity.Employee;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public class Accountant extends Employee {
    private String name;
    public Accountant(String name) {
        this.name = name;
    }
    public void settleSalary(Employee employee) {
        System.out.println(name + "为" + employee.getName() + "结算本月工资：" + employee.calculateSalary() + "元");
    }

    @Override
    public int calculateSalary() {
        return basicSalary;
    }

    @Override
    public String getReason() {
        return "";
    }
}
