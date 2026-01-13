import word.*;

import java.util.Scanner;



/**
 * @author jh
 * @project
 * @time 2026/1/4
 */
public class Main {
    public static void main(String[] args) {
        Phone huaweiMate40 = new Phone("黑色", "华为Mate40", "华为技术有限公司", 200);
        System.out.println("华为手机信息：");
        System.out.println("颜色：" + huaweiMate40.getColor() + "，品牌：" + huaweiMate40.getBrand() + "，重量：" + huaweiMate40.getWeight() + "g");
        huaweiMate40.call();
        System.out.println("------------------------");
        Phone honorV70 = new Phone("白色", "荣耀v70", "荣耀终端有限公司", 189);
        System.out.println("荣耀手机信息：");
        System.out.println("颜色：" + honorV70.getColor() + "，品牌：" + honorV70.getBrand() + "，重量：" + honorV70.getWeight() + "g");
        honorV70.sendMessge();
        Student liBai = new Student("李白", 17);
        Wangguang baiJuyi = new Wangguang("白居易");
        baiJuyi.checkAge(liBai);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== 停车场收费系统 =====");
            System.out.println("1. 车辆驶入");
            System.out.println("2. 车辆驶出");
            System.out.println("3. 退出系统");
            System.out.print("请选择操作：");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("请输入车牌号码：");
                    String inCarNum = sc.nextLine();
                    System.out.print("请输入车型（大型车，小型车）：");
                    String type = sc.next();
                    boolean isLarge = false;
                    if (type.equals("大型车")) {
                         isLarge = true;
                    }
                    if(type.equals("小型车")){
                        isLarge = false;
                    }
                    Car inCar = new Car(inCarNum, isLarge);
                    ParkingLot.inCar(inCar);
                    break;
                case 2:
                    System.out.print("请输入车牌号码：");
                    String outCarNum = sc.nextLine();
                    Car outCar = new Car(outCarNum, false);
                    ParkingLot.outCar(outCar);
                    break;
                case 3:
                    System.out.println("系统已退出");
                    sc.close();
                    return;
                default:
                    System.out.println("输入错误！");
            }
        }
    }
}

