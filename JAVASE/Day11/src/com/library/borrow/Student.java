package com.library.borrow;

/**
 * @author jh
 * @project PACKAGE_NAME
 * @time 2026/1/12
 */
public class Student {
    private String name;
    private BorrowCrad card;
    public Student(String name, BorrowCrad card) {
        this.name = name;
        this.card = card;
    }
    public String getName() {
        return name;
    }
    public BorrowCrad getCard() {
        return card;
    }
}
