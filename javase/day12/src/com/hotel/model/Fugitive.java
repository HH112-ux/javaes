package com.hotel.model;

/**
 * @author jh
 * @project com.hotel.model
 * @time 2026/1/13
 */
public class Fugitive {private String idCard;
    private String name;
    public Fugitive(String idCard, String name) {
        this.idCard=idCard;
        this.name=name;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard=idCard;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
}
