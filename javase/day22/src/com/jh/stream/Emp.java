package com.jh.stream;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class Emp {
    private String name;
    private Integer age;
    private Integer sal;

    public Emp(String name, Integer age, Integer sal) {
        this.name = name;
        this.age = age;
        this.sal = sal;
    }

    public Emp() {
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
}
