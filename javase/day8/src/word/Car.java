package word;

/**
 * @author jh
 * @project word
 * @time 2026/1/4
 */
public class Car {
    private String carNumber;
    private boolean largeV;
    private int inHour,inMin,outHour,outMin;
    public Car(String carNumber, boolean islargeV) {
        this.carNumber = carNumber;
        this.largeV = islargeV;

    }
    public String getCarNumber() {
        return carNumber;
    }
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public boolean isLargeV() {
        return largeV;
    }
    public void setLargeV(boolean largeV) {
        this.largeV = largeV;
    }

    public int getInHour() {
        return inHour;
    }
    public void setInHour(int inHour) {
        this.inHour = inHour;
    }
    public int getInMin() {
        return inMin;
    }
    public void setInMin(int inMin) {
        this.inMin = inMin;
    }
    public int getOutHour() {
        return outHour;
    }
    public void setOutHour(int outHour) {
        this.outHour = outHour;
    }
    public int getOutMin() {
        return outMin;
    }
    public void setOutMin(int outMin) {
        this.outMin = outMin;
    }


}
