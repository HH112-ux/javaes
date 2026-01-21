package com.company;

import java.util.ArrayList;
import java.util.List;

import static com.company.Employee.sortEmployees;

/**
 * @author jh
 * @project com.company
 * @time 2026/1/21
 */
public class Test {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("T1032", "李白", "男", 25, "2021-12-20", 10000, "吉林省长春市"));
        employees.add(new Employee("C1048", "杜甫", "女", 30, "2017-10-10", 12000, "江苏省南京市"));
        employees.add(new Employee("C1052", "白居易", "男", 28, "2019-05-22", 15000, "江苏省苏州市"));
        employees.add(new Employee("T1035", "李清照", "女", 31, "2015-04-12", 14000, "吉林省吉林市"));
        employees.add(new Employee("C1066", "陆游", "男", 40, "2019-05-22", 20000, "江苏省南京市"));
        employees.add(new Employee("C1088", "贺知章", "男", 35, "2012-03-30", 16000, "上海市"));
        sortEmployees(employees);
        List<Employee> top3 = employees.subList(0, 3);
        for (Employee emp : top3) {
            System.out.println(emp);
        }
    }
}
