package com.time.datetime.entity;

import com.time.datetime.util.DateUtil;

import java.time.LocalDate;

/**
 * @author jh
 * @project com.time.datetime.entity
 * @time 2026/1/19
 */
public class Human {
    private String name;
    private LocalDate birthDate;

    public Human(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public long getLiveDay() {
        LocalDate now = LocalDate.now();
        return DateUtil.calculateBetween(birthDate, now, "day");
    }


}
