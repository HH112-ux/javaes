package com.company.demo;

import com.company.entity.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.company.entity.Phone.*;

/**
 * @author jh
 * @project com.company.demo
 * @time 2026/1/21
 */
public class Test {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("T1032", "李白", "男", 25, LocalDate.of(2021, 12, 20), new BigDecimal("10000"), "吉林省长春市"));
        employees.add(new Employee("C1048", "杜甫", "女", 30, LocalDate.of(2017, 10, 10), new BigDecimal("12000"), "江苏省南京市"));
        employees.add(new Employee("C1052", "白居易", "男", 28, LocalDate.of(2019, 5, 22), new BigDecimal("15000"), "江苏省苏州市"));
        employees.add(new Employee("T1035", "李清照", "女", 31, LocalDate.of(2015, 4, 12), new BigDecimal("14000"), "吉林省吉林市"));
        employees.add(new Employee("C1066", "陆游", "男", 40, LocalDate.of(2009, 6, 15), new BigDecimal("20000"), "江苏省南京市"));
        employees.add(new Employee("C1088", "贺知章", "男", 35, LocalDate.of(2012, 3, 30), new BigDecimal("16000"), "上海市"));

        BigDecimal testTotal = BigDecimal.ZERO;
        BigDecimal devTotal = BigDecimal.ZERO;
        int testCount = 0, devCount = 0;
        for (Employee emp : employees) {
            if (emp.getId().startsWith("T")) {
                testTotal = testTotal.add(emp.getSalary());
                testCount++;
            } else if (emp.getId().startsWith("C")) {
                devTotal = devTotal.add(emp.getSalary());
                devCount++;
            }
        }

        BigDecimal testAvg = testTotal.divide(new BigDecimal(testCount), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal devAvg = devTotal.divide(new BigDecimal(devCount), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("测试部门工资总额：" + testTotal + "，平均工资：" + testAvg);
        System.out.println("研发部门工资总额：" + devTotal + "，平均工资：" + devAvg);

        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            Employee emp = it.next();
            if ("上海市".equals(emp.getWorkAddress())) {
                it.remove();
            }
        }

        LocalDate now = LocalDate.now();
        BigDecimal raiseRate = new BigDecimal("1.1");
        for (Employee emp : employees) {
            long months = ChronoUnit.MONTHS.between(emp.getInDate(), now);
            if (months > 70) {
                emp.setSalary(emp.getSalary().multiply(raiseRate));
            }
        }
        System.out.println("\n最终员工列表：");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        Set<String> phoneSet = generatePhoneNumbers(1000);
        List<String> phoneList = new ArrayList<>(phoneSet);

        List<String> luckyNumbers = drawNumbers(phoneList, 4);

        System.out.println("脱敏后的中奖号码：");
        for (String phone : luckyNumbers) {
            System.out.println(maskPhone(phone));
        }


    }
}


