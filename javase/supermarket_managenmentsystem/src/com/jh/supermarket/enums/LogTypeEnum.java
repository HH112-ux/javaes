package com.jh.supermarket.enums;


public enum LogTypeEnum {
    SYSTEM_LOG(1, "系统管理模块日志"),
    CASHIER_LOG(2, "收银员管理模块日志"),
    PRODUCT_LOG(3, "商品管理模块日志"),
    TYPE_LOG(4, "商品类型管理模块日志"),
    CASH_LOG(5, "收银模块日志");

    private final int code;
    private final String desc;

    LogTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        for (LogTypeEnum e : values()) {
            if (e.code == code) {
                return e.desc;
            }
        }
        return "未知日志类型";
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}