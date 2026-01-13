package com.employee.management.test;

import com.employee.management.entity.Employee;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public class Tseter extends Employee {
    public Tseter(String name, int basicSalary,int KPI) {
        super(name, basicSalary);
        this.KPI = KPI;
    }

    public int calculateSalary() {
        return basicSalary + super.KPI ;
    }

    @Override
    public String getReason() {
        return "bug为检测出";
    }

    @Override
    public void setKPI(int KPI) {
        super.setKPI(KPI);
    }
}
