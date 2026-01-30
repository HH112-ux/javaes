package com.jh.employeetest;

import com.jh.entity.Employee;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jh
 * @project com.jh.employeetest
 * @time 2026/1/30
 */
public class EmployeeTest {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Y1012", "李白", "男", 29, 12000, "研发部", LocalDate.of(2016, 10, 10)),
                new Employee("C2404", "杜甫", "男", 28, 14000, "产品部", LocalDate.of(2017, 9, 5)),
                new Employee("Y1035", "白居易", "男", 27, 11000, "研发部", LocalDate.of(2018, 5, 21)),
                new Employee("Y1089", "李商隐", "男", 29, 15000, "研发部", LocalDate.of(2016, 3, 19)),
                new Employee("C2675", "李清照", "女", 24, 8000, "产品部", LocalDate.of(2021, 7, 16)),
                new Employee("X3021", "陆游", "男", 32, 18000, "项目部", LocalDate.of(2014, 12, 20))
        );

        // 1. 公司设立了哪些部门
        List<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
        System.out.println("1. 公司部门：" + departments);

        // 2. 获取入职日期最早的员工信息
        Optional<Employee> earliestHire = employees.stream()
                .min(Comparator.comparing(Employee::getHireDate));
        System.out.println("2. 入职最早员工：" + earliestHire.orElse(null));

        // 3. 计算研发部门的总工资数和平均工资数
        Map<String, Integer> devDeptSalary = employees.stream()
                .filter(e -> "研发部".equals(e.getDepartment()))
                .collect(Collectors.teeing(
                        Collectors.summingInt(Employee::getSalary),
                        Collectors.averagingInt(Employee::getSalary),
                        (sum, avg) -> Map.of("总工资", sum, "平均工资", (int)avg.doubleValue())
                ));
        System.out.println("3. 研发部工资统计：" + devDeptSalary);

        // 4. 获取所有姓李的员工，计算人数
        long liCount = employees.stream()
                .filter(e -> e.getName().startsWith("李"))
                .count();
        System.out.println("4. 姓李的员工人数：" + liCount);

        // 5. 将所有员工按年龄升序，年龄相同按工资升序
        List<Employee> sortedByAgeAndSalary = employees.stream()
                .sorted(Comparator.comparing(Employee::getAge)
                        .thenComparing(Employee::getSalary))
                .toList();
        System.out.println("5. 按年龄和工资升序：" + sortedByAgeAndSalary);

        // 6. 将所有员工按入职日期降序
        List<Employee> sortedByHireDateDesc = employees.stream()
                .sorted(Comparator.comparing(Employee::getHireDate).reversed())
                .toList();
        System.out.println("6. 按入职日期降序：" + sortedByHireDateDesc);

        // 7. 将员工按姓名长度分组
        Map<Integer, List<Employee>> groupByNameLength = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getName().length()));
        System.out.println("7. 按姓名长度分组：" + groupByNameLength);

        // 8. 将所有产品部门工资不足10000的员工，加薪1000元
        List<Employee> productDeptRaise = employees.stream()
                .peek(e -> {
                    if ("产品部".equals(e.getDepartment()) && e.getSalary() < 10000) {
                        System.out.println("8.为员工 " + e.getName() + " 加薪1000元，原工资：" + e.getSalary() + " → 新工资：" + (e.getSalary() + 1000));
                    }
                })
                .toList();

        // 9. 返回所有员工的编号，按字母顺序升序排列
        List<String> sortedIds = employees.stream()
                .map(Employee::getId)
                .sorted()
                .toList();
        System.out.println("9. 编号按字母升序：" + sortedIds);

        // 10. 获取所有员工姓名，得到一个字符串数组
        String[] namesArray = employees.stream()
                .map(Employee::getName)
                .toArray(String[]::new);
        System.out.println("10. 员工姓名数组：" + Arrays.toString(namesArray));
    }
}


