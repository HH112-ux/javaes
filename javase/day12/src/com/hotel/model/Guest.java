package com.hotel.model;

/**
 * @author jh
 * @project com.hotel.guest
 * @time 2026/1/13
 */
public class Guest {
    private String name;
    private int age;
    private String idcard;
    public Guest(String name, int age, String idcard) {
        this.name = name;
        this.age = age;
        this.idcard = idcard;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }


}
