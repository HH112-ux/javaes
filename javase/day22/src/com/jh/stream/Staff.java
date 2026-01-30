package com.jh.stream;

import java.util.Objects;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class Staff implements Comparable<Staff> {
    private String name;
    private Integer age;
    private Integer sal;

    public Staff() {
    }

    public Staff(String name, Integer age, Integer sal) {
        this.name = name;
        this.age = age;
        this.sal = sal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sal=" + sal + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(name, staff.name) && Objects.equals(age,
                staff.age) && Objects.equals(sal, staff.sal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sal);
    }

    @Override
    public int compareTo(Staff o) {
        return Integer.compare(this.age, o.age);
    }
}
