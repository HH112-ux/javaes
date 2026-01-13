/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/9
 */
public  class TaxSystem {
    public TaxSystem(){}
    public static  int getCarSumTaxs(Car[] cars) {
        int taxs = 0;
        if(cars != null && cars.length > 0) {
            for (Car car : cars) {
                taxs+=calculateTax(car);
            }
        }else
            System.out.println("未添加报税车辆");
        return taxs;
    }
    public static int calculateTax(Car cars) {
        double capacity = cars.getCarCapacity();
        if (capacity <= 1.0) {
            return 360;
        } else if (capacity <= 1.6) {
            return 420;
        } else if (capacity <= 2.0) {
            return 540;
        } else if (capacity <= 2.5) {
            return 800;
        } else if (capacity <= 3.0) {
            return 1800;
        } else if (capacity <= 4.0) {
            return 2800;
        } else {
            return 3600;
        }
    }
}
