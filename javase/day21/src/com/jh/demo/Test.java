package com.jh.demo;

import com.jh.entity.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jh
 * @project com.jh.demo
 * @time 2026/1/27
 */
public class Test {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Y1012", "李白", "男", 29, 12000, "研发部", LocalDate.of(2016, 10, 10)));
        employees.add(new Employee("C2404", "杜甫", "男", 28, 14000, "产品部", LocalDate.of(2017, 9, 5)));
        employees.add(new Employee("Y1035", "白居易", "男", 27, 11000, "研发部", LocalDate.of(2018, 5, 21)));
        employees.add(new Employee("Y1089", "李商隐", "男", 29, 15000, "研发部", LocalDate.of(2016, 3, 19)));
        employees.add(new Employee("C2675", "李清照", "女", 24, 8000, "产品部", LocalDate.of(2021, 7, 16)));
        employees.add(new Employee("X3021", "陆游", "男", 32, 18000, "项目部", LocalDate.of(2014, 12, 20)));

        boolean productAllSalaryOver10000 = employees.stream()
                .filter(e -> "产品部".equals(e.getDepartment()))
                .allMatch(e -> e.getSalary() > 10000);
        System.out.println("产品部所有员工工资都超过10000元：" + productAllSalaryOver10000);

        boolean reseachAllMale = employees.stream()
                .filter(e -> "研发部".equals(e.getDepartment()))
                .allMatch(e -> "男".equals(e.getGender()));
        System.out.println("研发部所有员工都是男性：" + reseachAllMale);

        LocalDate fiveYearsAgo = LocalDate.now().minus(5, ChronoUnit.YEARS);
        List<Employee> devQualifiedEmployees = employees.stream()
                .filter(e -> "研发部".equals(e.getDepartment()))
                .filter(e -> e.getSalary() > 10000)
                .filter(e -> e.getHireDate().isBefore(fiveYearsAgo) || e.getHireDate().isEqual(fiveYearsAgo))
                .toList();
        System.out.println("研发部工资超10000，入职5年以上的员工：" + devQualifiedEmployees);

        long noProjectCount = employees.stream()
                .filter(e -> !"项目部".equals(e.getDepartment()))
                .count();
        System.out.println("非项目部员工数量：" + noProjectCount);

        Optional<Double> maxSalary = employees.stream()
                .filter(e -> e.getHireDate().isBefore(fiveYearsAgo) || e.getHireDate().isEqual(fiveYearsAgo))
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println("入职5年以上员工的最高工资：" + maxSalary.orElse(0.0));

        Optional<Employee> youngestEmployee = employees.stream()
                .filter(e -> "研发部".equals(e.getDepartment()))
                .min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println("研发部年龄最小的员工：" + youngestEmployee.orElse(null));

        Set<Character> researchIdFirstChars = employees.stream()
                .filter(e -> "研发部".equals(e.getDepartment()))
                .map(e -> e.getId().charAt(0))
                .collect(Collectors.toSet());
        System.out.println("研发部员工编号开头字母：" + researchIdFirstChars);
    }
}


