package com.employee.management.service;

import com.employee.management.entity.Employee;
import com.employee.management.entity.Programmer;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public class Manager extends Employee {
    private String name;
    public Manager(String name) {
        this.name = name;
    }

    public void fireEmployee(Employee employee) {
        System.out.println(name + "要开除员工：" + employee.getName() + "，原因：" + employee.getReason());
        if (employee instanceof Programmer) {
            System.out.println("因为程序员比较难招聘，所以给" + employee.getName() + "一次机会");
        }
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
