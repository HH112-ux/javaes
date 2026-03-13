package com.jh.supermarket.servic.goods;

import com.jh.supermarket.bean.Type;
import com.jh.supermarket.enums.LogTypeEnum;
import com.jh.supermarket.util.DataUtil;
import com.jh.supermarket.util.FileUtil;
import com.jh.supermarket.util.IdUtil;

import java.util.Scanner;

import static com.jh.supermarket.util.DataUtil.addLog;
import static com.jh.supermarket.util.DataUtil.typeData;

public class TypeServic {

    private static final Scanner sc = new Scanner(System.in);

    public void showTypeList() {
        System.out.println("-----------商品类型列表----------");
        if (DataUtil.typeData.isEmpty()) {
            System.out.println("暂无商品分类数据！");
            addLog("查看", "暂无商品分类数据", true, LogTypeEnum.TYPE_LOG);
            return;
        }
        System.out.printf("%-5s %-20s%n", "ID", "分类名称");
        for (Type t : DataUtil.typeData) {
            System.out.printf("%-5s %-20s%n", t.getId(), t.getName());
        }
        System.out.println("共计" + DataUtil.typeData.size() + "条数据。");
        addLog("查看", "成功查看所有分类，共" + DataUtil.typeData.size() + "条", true, LogTypeEnum.TYPE_LOG);
    }

    public void addType() {
        System.out.println("-----------新镇商品分类----------");
        Type t = new Type();
        while (true) {
            System.out.print("请输入商品分类名称：");
            String name = sc.next();
            if (DataUtil.getTypeByname(name) != null) {
                System.out.println("分类名称已存在，请重新输入！");
                System.out.println("重新输入商品分类名称/退出新镇商品分类模块（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("新增", "新增商品分类, 增加失败，商品分类：" + name + "已存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("新增", "新增商品分类, 增加失败，商品分类：" + name + "已存在，主动退出", false, LogTypeEnum.PRODUCT_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("新增", "新增商品分类, 增加失败，商品分类：" + name + "已存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                }
            }
            t.setName(name);
            break;
        }
        DataUtil.typeData.add(t);
        System.out.println("新增分类成功！ID：" + t.getId() + "，名称：" + t.getName());
        FileUtil.saveTypes(typeData);
        addLog("新增", "成功新增分类，ID：" + t.getId() + "，名称：" + t.getName(), true, LogTypeEnum.TYPE_LOG);
    }

    public void delType() {
        System.out.println("-----------移除商品分类----------");
        if (DataUtil.typeData.isEmpty()) {
            System.out.println("暂无商品类");
            addLog("移除", "移除失败，暂无商品类", false, LogTypeEnum.TYPE_LOG);
            return;
        }
        this.showTypeList();
        String id;
        Type t;
        while (true) {
            System.out.print("请输入要移除的分类ID：");
            id = sc.next();
            t = DataUtil.getTypeById(id);
            if (t == null) {
                System.out.println("分类ID不存在，移除失败！");
                System.out.println("重新输入ID/退出移除商品分类模块（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("移除", "移除失败，ID：" + id + "不存在,重新输入", false, LogTypeEnum.TYPE_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("移除", "移除失败，ID：" + id + "不存在，主动退出", false, LogTypeEnum.TYPE_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("移除", "移除失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.TYPE_LOG);
                    continue;
                }
            }
            break;
        }
        String finalId = id;
        boolean hasProduct = DataUtil.productsData.stream().anyMatch(p -> finalId.equals(p.getTypeId()));
        if (hasProduct) {
            System.out.println("该分类下有商品，无法移除！");
            addLog("移除", "移除失败，ID：" + id + "下有关联商品", false, LogTypeEnum.TYPE_LOG);
            return;
        }
        DataUtil.typeData.remove(t);
        IdUtil.recycleTypeId(id);
        System.out.println("移除分类成功！ID：" + id + "，名称：" + t.getName());
        FileUtil.saveTypes(typeData);
        addLog("移除分类", "成功移除分类，ID：" + id + "，名称：" + t.getName(), true, LogTypeEnum.TYPE_LOG);
    }
}

