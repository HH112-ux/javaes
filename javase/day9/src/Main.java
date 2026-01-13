/**
 * @author jh
 * @project
 * @time 2026/1/9
 */

public class Main {
    public static void main(String[] args) {

        Car audi = new Car("奥迪", 3.2);
        Car magotan = new Car("迈腾", 2.8);
        Car bora = new Car("宝来", 1.6);

        Car[] cars = {audi, magotan, bora};

        System.out.println(audi.getCarName() + "（" + audi.getCarCapacity() + "升）：" + TaxSystem.calculateTax(audi) + "元");
        System.out.println(magotan.getCarName() + "（" + magotan.getCarCapacity() + "升）：" + TaxSystem.calculateTax(magotan) + "元");
        System.out.println(bora.getCarName() + "（" + bora.getCarCapacity() + "升）：" + TaxSystem.calculateTax(bora) + "元");
        System.out.println("总价为"+ TaxSystem.getCarSumTaxs(cars)+"元");
    }
}