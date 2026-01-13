package com.library.borrow;

/**
 * @author jh
 * @project word1
 * @time 2026/1/12
 */
public class Librarian {
    private String name;
    public Librarian(String name) {
        this.name = name;
    }
    public Book checkCard(Student student) {
        BorrowCrad card = student.getCard();
        if ("红色".equals(card.getColor()) && "A".equals(card.getType())) {
            return new Book("Java基础");
        } else if ("红色".equals(card.getColor()) && "B".equals(card.getType())) {
            return new Book("MySQL数据库");
        } else {
            return new Book("Java编程思想");
        }
    }
}
