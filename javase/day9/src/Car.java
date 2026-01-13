/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/9
 */
public class Car {
    private String carName;
    private double carCapacity;
    public Car(String carName, double carCapacity){
        this.carName = carName;
        this.carCapacity = carCapacity;
    }
    public String getCarName(){
        return carName;
    }
    public double getCarCapacity(){
        return carCapacity;
    }
    public void setCarName(String carName){
        this.carName = carName;
    }
    public void setCarCapacity(double carCapacity){
        this.carCapacity = carCapacity;
    }
}
