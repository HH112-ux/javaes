package com.jh.supermarket.servic.goods;

import com.jh.supermarket.bean.Product;
import com.jh.supermarket.enums.LogTypeEnum;
import com.jh.supermarket.util.FileUtil;
import com.jh.supermarket.util.IdUtil;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.jh.supermarket.util.DataUtil.*;

public class ProductServic {
    private static Scanner sc = new Scanner(System.in);

    public void showProduct() {
        System.out.println("-----------商品列表----------");
        System.out.printf("%-5s %-15s %-15s %-5s %-8s", "编号", "名称", "价格", "数量", "属性类别");
        if (productsData.isEmpty()) {
            System.out.println("暂无商品信息，请添加商品");
            addLog("查看", "查看所有商品信息失败，暂无商品信息", false, LogTypeEnum.PRODUCT_LOG);
            return;
        }
        for (Product p : productsData) {
            System.out.printf("%-5s %-15s %-15s %-5s %-8s %-8s %-18s %-20s",
                    p.getId(), p.getName(), p.getPrice(), p.getCount(), p.getTypeId());
        }
        System.out.println("-----------商品列表----------");
        System.out.println("共计" + productsData.size() + "条数据");
        addLog("查看", "查看所有商品的信息", true, LogTypeEnum.PRODUCT_LOG);
    }

    public void removeProduct() {
        System.out.println("-----------移除商品----------");
        this.showProduct();
        if (productsData.isEmpty()) {
            System.out.println("商品列表为空，无法移除");
            addLog("移除", "移除商品信息失败，商品列表为空", false, LogTypeEnum.PRODUCT_LOG);
            return;
        }
        String id;
        Product p;
        while (true) {
            System.out.println("请输入要删除商品的id");
            id = sc.next();
            p = getProductById(id);
            if (p == null) {
                System.out.println("该商品不存在，请重新输入id");
                System.out.println("重新输入id/退出入库系统（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("删除", "删除商品, 删除失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("删除", "删除商品, 删除失败，ID：" + id + "不存在，主动退出", false, LogTypeEnum.PRODUCT_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("删除", "删除商品, 删除失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                }
            }
            break;
        }
        productsData.remove(p);
        IdUtil.recycleProductId(id);
        System.out.println("成功移除商品 id：" + id + "名称：" + p.getName());
        FileUtil.saveProducts(productsData);
        addLog("移除", "移除id:" + id + "商品的信息", true, LogTypeEnum.PRODUCT_LOG);


    }

    public void addProduct() {
        System.out.println("-----------新增商品----------");
        String typeId = "";
        while (true) {
            System.out.println("请添加商品类型编号：");
            typeId = sc.next();
            if (getProductById(typeId) == null) {
                System.out.println("商品类型不存在");
                System.out.println("重新输入商品类型/退出新增商品模块（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("添加", "添加商品价格, 失败，商品类别" + typeId + "不存在,重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("添加", "添加商品价格, 失败，商品类别" + typeId + "不存在,主动退出", false, LogTypeEnum.PRODUCT_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("添加", "添加商品价格, 失败，商品类别" + typeId + "不存在,重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                }
            }
            break;
        }
        System.out.println("请添加商品名称：");
        String name = sc.next();
        if (getProductByname(name) != null) {
            Product product = getProductByname(name);
            System.out.println("该商品已存在，请输入数量");
            int count;
            while (true) {
                System.out.println("请添加商品数量：");
                count = sc.nextInt();
                if (count <= 0) {
                    System.out.println("商品数量必须大于0");
                    System.out.println("请重新输入");
                    addLog("添加", "添加已存在商品的信息,商品数量小于0，重新输入", false, LogTypeEnum.CASHIER_LOG);
                    continue;
                }
                break;
            }
            product.setCount(count);
        } else {
            int count;
            while (true) {
                System.out.println("请添加商品数量：");
                count = sc.nextInt();
                if (count <= 0) {
                    System.out.println("商品数量必须大于0");
                    System.out.println("请重新输入");
                    addLog("添加", "添加新商品的信息,商品数量小于0，重新输入", false, LogTypeEnum.CASHIER_LOG);
                    continue;
                }
                break;
            }
            BigDecimal price;
            while (true) {
                System.out.println("请添加商品数量：");
                price = sc.nextBigDecimal();
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("商品价格必须大于0");
                    System.out.println("请重新输入");
                    addLog("添加", "添加已存在商品的信息,商品价格小于0，重新输入", false, LogTypeEnum.CASHIER_LOG);
                    continue;
                }
                break;
            }
            Product p = new Product();
            p.setCount(count);
            p.setName(name);
            p.setPrice(price);
            p.setTypeId(typeId);
            productsData.add(p);
        }
        FileUtil.saveProducts(productsData);
        addLog("添加", "添加新商品的信息", true, LogTypeEnum.CASHIER_LOG);

    }

    public void updatePrice() {
        System.out.println("-----------修改商品价格----------");
        this.showProduct();
        if (productsData.isEmpty()) return;
        System.out.print("请输入要修改价格的商品ID：");
        Product p;
        String id;
        while (true) {
            id = sc.next();
            p = getProductById(id);
            if (p == null) {
                System.out.println("商品ID不存在，修改失败！");
                System.out.println("重新输入id/退出修改价格模块（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("修改价格", "入库失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("修改价格", "修改商品价格, 修改失败，ID：" + id + "不存在，主动退出", false, LogTypeEnum.PRODUCT_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("修改价格", "入库失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                }
            }
            break;
        }
        System.out.println("当前价格：" + p.getPrice() + "¥");
        BigDecimal oldPrice = p.getPrice();
        while (true) {
            System.out.print("请输入新价格：");
            BigDecimal newPrice = new BigDecimal(sc.next());
            if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("商品价格必须大于0");
                System.out.println("请重新输入");
                continue;
            }
            p.setPrice(newPrice);
            break;
        }
        FileUtil.saveProducts(productsData);
        addLog("修改价格", "商品ID：" + id + "原价" + oldPrice + "修改为" + p.getPrice() + "¥", true, LogTypeEnum.PRODUCT_LOG);
    }

    public void inProduct() {
        System.out.println("-----------出库商品----------");
        this.showProduct();
        if (productsData.isEmpty()) return;
        Product p;
        String id;
        while (true) {
            System.out.print("请输入要入库的商品ID：");
            id = sc.next();
            p = getProductById(id);
            if (p == null) {
                System.out.println("商品ID不存在，入库失败！");
                System.out.println("重新输入id/退出入库系统（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("商品入库", "入库失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("商品入库", "入库失败，ID：" + id + "不存在，主动退出", false, LogTypeEnum.PRODUCT_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("商品入库", "入库失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                }
            }
            break;
        }
        System.out.println("当前库存：" + p.getCount() + "个");
        int inCount;
        while (true) {
            System.out.print("请输入入库数量：");
            inCount = sc.nextInt();
            if (inCount <= 0) {
                System.out.println("入库数量必须大于0，入库失败！");
                addLog("商品入库", "商品ID：" + id + "入库数量≤0，入库失败,重新输入", false, LogTypeEnum.PRODUCT_LOG);
                System.out.println("亲重新输入入库数量");
                continue;
            }
            break;
        }
        p.setCount(p.getCount() + inCount);
        System.out.println("入库成功！当前库存：" + p.getCount() + "个");
        FileUtil.saveProducts(productsData);
        addLog("商品入库", "商品ID：" + id + "入库" + inCount + "个", true, LogTypeEnum.PRODUCT_LOG);
    }

    public void outProduct() {
        System.out.println("-----------出库商品----------");
        this.showProduct();
        if (productsData.isEmpty()) return;
        String id;
        Product p;
        while (true) {
            System.out.print("请输入要出库的商品ID：");
            id = sc.next();
            p = getProductById(id);
            if (p == null) {
                System.out.println("商品ID不存在，出库失败！");
                System.out.println("重新输入id/退出出库模块（Y/N）");
                String type = sc.next();
                if (type.equals("Y") || type.equals("y")) {
                    addLog("商品入库", "入库失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                } else if (type.equals("N") || type.equals("n")) {
                    addLog("商品入库", "入库失败，ID：" + id + "不存在，主动退出", false, LogTypeEnum.PRODUCT_LOG);
                    break;
                } else {
                    System.out.println("输入错误");
                    addLog("商品入库", "入库失败，ID：" + id + "不存在，重新输入", false, LogTypeEnum.PRODUCT_LOG);
                    continue;
                }
            }
            break;
        }
        System.out.println("当前库存：" + p.getCount() + "个");
        int outCount;
        while (true) {
            System.out.print("请输入出库数量：");
            outCount = sc.nextInt();
            if (outCount <= 0) {
                System.out.println("出库数量必须大于0，出库失败！");
                System.out.println("亲重新输入");
                addLog("商品出库", "商品ID：" + id + "出库数量≤0，出库失败,重新输入", false, LogTypeEnum.PRODUCT_LOG);
                continue;
            }
            if (outCount > p.getCount()) {
                System.out.println("出库数量大于库存，出库失败！");
                System.out.println("亲重新输入");
                addLog("商品出库", "商品ID：" + id + "出库数量，重新重新输入" + outCount + "大于库存"
                        + p.getCount(), false, LogTypeEnum.PRODUCT_LOG);
                continue;
            }
            break;
        }
        p.setCount(p.getCount() - outCount);
        System.out.println("出库成功！当前库存：" + p.getCount() + "个");
        FileUtil.saveProducts(productsData);
        addLog("商品出库", "商品ID：" + id + "出库" + outCount + "个", true, LogTypeEnum.PRODUCT_LOG);
    }
}
