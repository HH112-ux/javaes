package com.jh.supermarket.servic.system;

import com.jh.supermarket.bean.Cashier;
import com.jh.supermarket.enums.LogTypeEnum;
import com.jh.supermarket.util.CheckUtil;
import com.jh.supermarket.util.DataUtil;
import com.jh.supermarket.util.FileUtil;
import com.jh.supermarket.util.IdUtil;

import java.util.Scanner;

import static com.jh.supermarket.util.DataUtil.addLog;
import static com.jh.supermarket.util.DataUtil.cashiersData;


public class CashierServic {
    static Scanner sc = new Scanner(System.in);

    public void showCashiers() {
        System.out.println("-----------收银员列表----------");
        System.out.printf("%-5s %-15s %-15s %-5s %-8s %-8s %-18s %-20s", "ID", "账号", "密码", "性别", "年龄", "姓名", "联系方式", "地址");
        if (cashiersData.isEmpty()) {
            System.out.println("暂无收银员信息，请添加收银员");
            addLog("查看", "查看所有收银员的信息失败，暂无收银员信息", false, LogTypeEnum.CASHIER_LOG);
            return;
        }
        for (Cashier c : cashiersData) {
            System.out.printf("%-5s %-15s %-15s %-5s %-8s %-8s %-18s %-20s",
                    c.getId(), c.getAccount(), c.getPassword(), c.getSex(), c.getAge(), c.getName(), c.getPhoneNumber(), c.getAddress());
        }
        System.out.println("-----------收银员列表----------");
        System.out.println("共计" + cashiersData.size() + "条数据");
        addLog("查看", "查看所有收银员的信息", true, LogTypeEnum.CASHIER_LOG);
    }

    public void addCashiers() {
        System.out.println("-----------新增收银员----------");
        Cashier c = new Cashier();
        while (true) {
            System.out.println("请添加账号：");
            String account = sc.next();
            if (DataUtil.getCashierByAccount(account) == null) {
                c.setAccount(account);
                break;
            }
            System.out.println("账号重复，请重新输入账号");
            System.out.println("重新输入账号/退出登录收银系统模块（Y/N）");
            String type = sc.next();
            if (type.equals("Y") || type.equals("y")) {
                addLog("添加", "添加新收银员的信息,添加账号失败，账号重复，重新输入", false, LogTypeEnum.CASHIER_LOG);
            } else if (type.equals("N") || type.equals("n")) {
                addLog("添加", "添加新收银员的信息,添加账号失败，账号重复，主动退出", false, LogTypeEnum.CASHIER_LOG);
                break;
            } else {
                System.out.println("输入错误");
                addLog("添加", "添加新收银员的信息,添加账号失败，账号重复，重新输入", false, LogTypeEnum.CASHIER_LOG);
            }
        }
        while (true) {
            System.out.println("请输入密码：（包含大小写，数字和特殊符且要大于6位数）");
            String password = sc.next();
            if (CheckUtil.checkPassword(password)) {
                c.setPassword(password);
                break;
            }
            addLog("添加", "添加新收银员的信息,写入密码失败，密码安全性太低不符合规则，重新输入", false, LogTypeEnum.CASHIER_LOG);
            System.out.println("密码安全性太低不符合规则请重新输入");
        }
        while (true) {
            System.out.print("请输入收银员手机号（11位）：");
            String phone = sc.next();
            if (CheckUtil.checkPhone(phone)) {
                c.setPhoneNumber(phone);
                break;
            }
            addLog("添加", "添加新收银员的信息,添加电话失败，电话不符合格式，重新输入", false, LogTypeEnum.CASHIER_LOG);
            System.out.println("手机号格式错误，请重新输入！");
        }
        System.out.print("请输入收银员家庭地址：");
        sc.nextLine();
        c.setAddress(sc.nextLine());

        cashiersData.add(c);
        FileUtil.saveCashiers(cashiersData);
        System.out.println("新增收银员成功！ID：" + c.getId());
        addLog("添加", "添加新收银员的信息", true, LogTypeEnum.CASHIER_LOG);

    }

    public void removeCashier() {
        System.out.println("-----------移除收银员----------");

        if (cashiersData.isEmpty()) {
            System.out.println("收银员列表为空，无法移除");
            addLog("移除", "移除收银员的信息失败，收银员列表为空", false, LogTypeEnum.CASHIER_LOG);
            return;
        }

        while (true) {
            System.out.println("请输入要删除收银员的id");
            String id = sc.next();
            Cashier c = DataUtil.getCashierById(id);
            if (c == null) {
                System.out.println("该收银员不存在，请重新输入id");
                System.out.println("重新输入账号/退出登录收银系统模块（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("移除", "移除失败，移除id:" + id + "收银员不存在，重新输入", false, LogTypeEnum.CASHIER_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("移除", "移除失败，移除id:" + id + "收银员不存在，主动退出", false, LogTypeEnum.CASHIER_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("移除", "移除失败，移除id:" + id + "收银员不存在，重新输入", false, LogTypeEnum.CASHIER_LOG);
                    continue;
                }
            }
            cashiersData.remove(c);
            IdUtil.recycleCashierId(id);
            System.out.println("成功移除收银员 id：" + id + "姓名：" + c.getName());
            FileUtil.saveCashiers(cashiersData);
            addLog("移除", "移除id:" + id + "收银员的信息", true, LogTypeEnum.CASHIER_LOG);
            break;
        }
    }



}


