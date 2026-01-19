package com.time.datetime.demo;

import com.time.datetime.entity.Employee;
import com.time.datetime.entity.Human;
import com.time.datetime.entity.Player;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author jh
 * @project com.time.datetime.demo
 * @time 2026/1/19
 */
public class Demo {
    public static void main(String[] args) {
        //test1();
        test2();
        //test3();

    }

    private static void test2() {
        Player[] players = {
                new Player("李白", LocalDate.of(2026, 9, 5)),
                new Player("李信", LocalDate.of(2026, 9, 10)),
                new Player("白起", LocalDate.of(2026, 9, 28)),
                new Player("李元芳", LocalDate.of(2026, 10, 10))
        };

        for (Player player : players) {
            System.out.println(player);
        }
    }

    private static void test3() {
        Employee[] employees = {
                new Employee("李白", LocalDate.of(2018, 2, 4), 8000),
                new Employee("杜甫", LocalDate.of(2012, 5, 6), 12000),
                new Employee("白居易", LocalDate.of(2014, 4, 10), 10000),
                new Employee("陆游", LocalDate.of(2016, 10, 11), 9000)
        };

        System.out.println("原始工资");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        for (Employee emp : employees) {
            if (emp.isEligibleForRaise()) {
                emp.raiseSalary();
            }
        }
        System.out.println("张薪后工资");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private static void test1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你的生日 ");
        int year = sc.nextInt();
        int month = sc.nextInt();
        int date = sc.nextInt();
        sc.close();
        Human jh = new Human("蒋辉", LocalDate.of(year, month, date));
        System.out.println(jh.getLiveDay());
    }
}
