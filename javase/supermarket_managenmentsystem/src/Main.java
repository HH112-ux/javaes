import com.jh.supermarket.servic.Checkout.CheckoutServic;
import com.jh.supermarket.servic.goods.ProductServic;
import com.jh.supermarket.servic.goods.TypeServic;
import com.jh.supermarket.servic.system.CashierServic;
import com.jh.supermarket.servic.system.Logservic;
import com.jh.supermarket.util.DataUtil;
import com.jh.supermarket.util.FileUtil;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Properties prop = new Properties();
    static String userName, password;
    static CheckoutServic checkoutServic = new CheckoutServic();
    static ProductServic productServic = new ProductServic();
    static TypeServic typeServic = new TypeServic();
    static CashierServic cashierServic = new CashierServic();

    public static void main(String[] args) {
        loadData();

        try {
            prop.load(Main.class.getClassLoader().getResourceAsStream("dp.properties"));
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            System.out.println("请输入账号密码");
            System.out.println("账号：");
            String user = sc.next();
            System.out.println("密码：");
            String passWord = sc.next();
            if (!user.equals(userName) || !passWord.equals(password)) {
                System.out.println("登录失败");
                System.exit(0);
            }

            System.out.println("登录成功，欢迎进入超市管理系统！");

            startStatisticsThread();

            while (true) {
                showMainMenu();
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        showCheckoutMenu();
                        break;
                    case 2:
                        showProductMenu();
                        break;
                    case 3:
                        showTypeMenu();
                        break;
                    case 4:
                        showCashierMenu();
                        break;
                    case 5:
                        Logservic.showLogData();
                        break;
                    case 6:
                        System.out.println("正在保存数据...");
                        FileUtil.saveAllData();
                        System.out.println("感谢使用超市管理系统，再见！");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("无效选项，请重新选择！");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("配置文件加载失败：" + e.getMessage());
            System.exit(1);
        }
    }

    private static void loadData() {
        DataUtil.cashiersData = FileUtil.loadCashiers();
        DataUtil.productsData = FileUtil.loadProducts();
        DataUtil.typeData = FileUtil.loadTypes();
        DataUtil.ordersData = FileUtil.loadOrders();
        DataUtil.logsData = FileUtil.loadLogs();
        FileUtil.loadIdPool();
        DataUtil.initMaxId();
    }

    private static void startStatisticsThread() {
        Thread statisticsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);

                    Map<String, Object> stockInfo = DataUtil.totalStock();
                    System.out.println("\n[库存统计] 当前物品总数：" + stockInfo.get("totalCount") +
                            "，物品总金额：" + stockInfo.get("totalAmount") + "元，物品平均价格：" +
                            stockInfo.get("avgPrice") + "元");

                    Map<String, Object> saleInfo = DataUtil.getSaleStat();
                    System.out.println("[销售统计] 当前销售物品总数：" + saleInfo.get("totalSaleCount") +
                            "，销售物品总金额：" + saleInfo.get("totalSaleAmount") + "元，销售平均价格：" +
                            saleInfo.get("avgSalePrice") + "元");
                } catch (InterruptedException e) {
                    System.out.println("统计线程被中断");
                    break;
                }
            }
        });
        statisticsThread.setDaemon(true);
        statisticsThread.start();

        Map<String, Object> stockInfo = DataUtil.totalStock();
        System.out.println("\n[库存统计] 当前物品总数：" + stockInfo.get("totalCount") +
                "，物品总金额：" + stockInfo.get("totalAmount") + "元，物品平均价格：" +
                stockInfo.get("avgPrice") + "元");

        Map<String, Object> saleInfo = DataUtil.getSaleStat();
        System.out.println("[销售统计] 当前销售物品总数：" + saleInfo.get("totalSaleCount") +
                "，销售物品总金额：" + saleInfo.get("totalSaleAmount") + "元，销售平均价格：" +
                saleInfo.get("avgSalePrice") + "元");
    }

    private static void showMainMenu() {
        System.out.println("\n========== 超市管理系统主菜单 ==========");
        System.out.println("1. 收银模块");
        System.out.println("2. 商品管理模块");
        System.out.println("3. 商品类型管理模块");
        System.out.println("4. 收银员管理模块");
        System.out.println("5. 日志管理模块");
        System.out.println("6. 退出系统");
        System.out.println("==================================");
        System.out.print("请选择操作：");
    }

    private static void showCheckoutMenu() {
        if (!checkoutServic.isCashierLoggedIn()) {
            System.out.println("进入收银模块前需要先登录收银系统");
            checkoutServic.loginCheckout();

            if (!checkoutServic.isCashierLoggedIn()) {
                System.out.println("收银员登录失败，无法进入收银模块");
                return;
            }
        }

        while (true) {
            System.out.println("\n========== 收银模块 ==========");
            System.out.println("1. 登录收银系统（切换收银员）");
            System.out.println("2. 新增订单");
            System.out.println("3. 查看订单列表");
            System.out.println("4. 查找订单");
            System.out.println("5. 删除订单");
            System.out.println("6. 退出收银模块");
            System.out.println("=============================");
            System.out.print("请选择操作：");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    checkoutServic.logoutCheckout();
                    checkoutServic.loginCheckout();
                    if (!checkoutServic.isCashierLoggedIn()) {
                        System.out.println("收银员登录失败，返回主菜单");
                        return;
                    }
                    break;
                case 2:
                    if (checkoutServic.isCashierLoggedIn()) {
                        checkoutServic.addOrder();
                    } else {
                        System.out.println("请先登录收银系统！");
                        checkoutServic.loginCheckout();
                    }
                    break;
                case 3:
                    if (checkoutServic.isCashierLoggedIn()) {
                        checkoutServic.showOrderList();
                    } else {
                        System.out.println("请先登录收银系统！");
                        checkoutServic.loginCheckout();
                    }
                    break;
                case 4:
                    if (checkoutServic.isCashierLoggedIn()) {
                        checkoutServic.findOrder();
                    } else {
                        System.out.println("请先登录收银系统！");
                        checkoutServic.loginCheckout();
                    }
                    break;
                case 5:
                    if (checkoutServic.isCashierLoggedIn()) {
                        checkoutServic.delOrder();
                    } else {
                        System.out.println("请先登录收银系统！");
                        checkoutServic.loginCheckout();
                    }
                    break;
                case 6:
                    checkoutServic.logoutCheckout();
                    return;
                default:
                    System.out.println("无效选项，请重新选择！");
                    break;
            }
        }
    }

    private static void showProductMenu() {
        while (true) {
            System.out.println("\n========== 商品管理模块 ==========");
            System.out.println("1. 查看商品列表");
            System.out.println("2. 新增商品");
            System.out.println("3. 删除商品");
            System.out.println("4. 修改商品价格");
            System.out.println("5. 商品入库");
            System.out.println("6. 商品出库");
            System.out.println("7. 返回主菜单");
            System.out.println("================================");
            System.out.print("请选择操作：");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    productServic.showProduct();
                    break;
                case 2:
                    productServic.addProduct();
                    break;
                case 3:
                    productServic.removeProduct();
                    break;
                case 4:
                    productServic.updatePrice();
                    break;
                case 5:
                    productServic.inProduct();
                    break;
                case 6:
                    productServic.outProduct();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("无效选项，请重新选择！");
                    break;
            }
        }
    }

    private static void showTypeMenu() {
        while (true) {
            System.out.println("\n========== 商品类型管理模块 ==========");
            System.out.println("1. 查看商品类型列表");
            System.out.println("2. 新增商品类型");
            System.out.println("3. 删除商品类型");
            System.out.println("4. 返回主菜单");
            System.out.println("===================================");
            System.out.print("请选择操作：");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    typeServic.showTypeList();
                    break;
                case 2:
                    typeServic.addType();
                    break;
                case 3:
                    typeServic.delType();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("无效选项，请重新选择！");
                    break;
            }
        }
    }

    private static void showCashierMenu() {
        while (true) {
            System.out.println("\n========== 收银员管理模块 ==========");
            System.out.println("1. 查看收银员列表");
            System.out.println("2. 新增收银员");
            System.out.println("3. 删除收银员");
            System.out.println("4. 返回主菜单");
            System.out.println("=================================");
            System.out.print("请选择操作：");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    cashierServic.showCashiers();
                    break;
                case 2:
                    cashierServic.addCashiers();
                    break;
                case 3:
                    cashierServic.removeCashier();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("无效选项，请重新选择！");
                    break;
            }
        }
    }
}