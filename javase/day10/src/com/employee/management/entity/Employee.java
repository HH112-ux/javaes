package com.employee.management.entity;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public abstract class Employee {
    private String name;
    protected int basicSalary;
    protected int KPI;
    public Employee(){}
    public Employee(String name, int basicSalary) {
        this.name = name;
        this.basicSalary = basicSalary;
    }
    public String getName(){
        return name;
    }
    public  void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }
    public void setKPI(int KPI){
        this.KPI = KPI;
    }
    public abstract int calculateSalary();
    public abstract String getReason();

}
