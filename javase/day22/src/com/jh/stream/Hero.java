package com.jh.stream;

import java.util.Objects;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class Hero {
    private  String name;
    private  Integer blood;

    public Hero(String name, Integer blood) {
        this.name = name;
        this.blood = blood;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }
    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", blood=" + blood +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(name, hero.name) &&
                Objects.equals(blood, hero.blood);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, blood);
    }
}
