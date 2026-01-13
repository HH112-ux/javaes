package word;

import java.util.Scanner;

/**
 * @author jh
 * @project word
 * @time 2026/1/4
 */

public class ParkingLot {
    private static final int CAPACITY = 50;
    private static Car[] cars = new Car[CAPACITY];
    private static int carCounters = 0;
    private static Scanner sc = new Scanner(System.in);

    public ParkingLot() {
    }

    public static void inCar(Car car) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null) {
                if (cars[i].getCarNumber().equals(car.getCarNumber())) {
                System.out.println("车牌号重复，请检查车牌号");
                return;
                }
            }
        }
        if (carCounters >= CAPACITY) {
            System.out.println("停车场已满");
            return;
        }
        System.out.println("请输入驶入时间（几点几分）");
        int inHour = sc.nextInt();
        int inMin = sc.nextInt();
        if (!checkTime(inHour, inMin)) {
            System.out.println("驶入时间输入错误");
            return;
        }

        car.setInHour(inHour);
        car.setInMin(inMin);
        cars[carCounters] = car;
        carCounters++;
        System.out.println("车牌" + car.getCarNumber() + "驶入成功");
    }

    public static void outCar(Car car) {
        if (carCounters == 0) {
            System.out.println("停车场内没有这个车");
            return;
        }

        int carIndex = -1;
        for (int i = 0; i < carCounters; i++) {
            if (cars[i].getCarNumber().equals(car.getCarNumber())) {
                carIndex = i;
                break;
            }
        }
        if (carIndex == -1) {
            System.out.println("未找到该车牌的车辆");
            return;
        }

        System.out.println("请输入驶出时间（几点 几分）");
        int outHour = sc.nextInt();
        int outMin = sc.nextInt();
        if (!checkTime(outHour, outMin)) {
            System.out.println("驶出时间输入错误");
            return;
        }

        cars[carIndex].setOutHour(outHour);
        cars[carIndex].setOutMin(outMin);
        calculateFee(cars[carIndex]);
        removeCar(carIndex);
    }

    private static boolean checkTime(int hour, int min) {
        return hour >= 0 && hour <= 23 && min >= 0 && min <= 59;
    }

    private static void calculateFee(Car car) {
        int inTotal = car.getInHour() * 60 + car.getInMin();
        int outTotal = car.getOutHour() * 60 + car.getOutMin();
        int min = outTotal - inTotal;

        if (min <= 0) {
            System.out.println("驶出时间不能早于驶入时间，无法计算费用");
            return;
        }

        int hours = min % 60 == 0 ? min / 60 : min / 60 + 1;
        double RNB = 0.0;

        if (car.isLargeV()) {
            RNB = hours <= 1 ? 15 : 15 + (hours - 1) * 8;
        } else {
            RNB = hours <= 1 ? 8 : 8 + (hours - 1) * 5;
        }


        System.out.println("停车时长：" + min + "分钟（折算" + hours + "小时）");
        System.out.println("停车费用：" + RNB + "元");
        System.out.println("车牌" + car.getCarNumber() + "驶出成功");
    }

    private static void removeCar(int index) {
        for (int i = index; i < carCounters - 1; i++) {
            cars[i] = cars[i + 1];
        }
        cars[carCounters - 1] = null;
        carCounters--;
    }
}
