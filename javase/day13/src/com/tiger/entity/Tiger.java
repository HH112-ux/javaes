package com.tiger.entity;

/**
 * @author jh
 * @project com.tiger.entity
 * @time 2026/1/15
 */
public class Tiger implements Cloneable {
    private double length;
    private double weight;
    public Tiger(double length, double weight) {
        this.length = length;
        this.weight = weight;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;
        Tiger tiger = (Tiger) obj;
        return Double.compare(tiger.length, length) == 0 && Double.compare(tiger.weight, weight) == 0;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(length) + Double.hashCode(weight) * 31;
    }
    @Override
    public String toString() {
        return "Tiger{身长=" + length + "米, 体重=" + weight + "千克}";
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
