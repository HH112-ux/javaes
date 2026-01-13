package com.employee.management.entity;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public class Designer extends Employee {
    public Designer(String name, int basicSalary) {
        super(name, basicSalary);
    }

    @Override
    public int calculateSalary() {
        return basicSalary;
    }

    @Override
    public String getReason() {
        return "设计不合理";
    }
}
