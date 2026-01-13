package com.library.borrow;

/**
 * @author jh
 * @project word1
 * @time 2026/1/12
 */
public class Tset1 {
    public static void main(String[] args) {
    BorrowCrad card = new BorrowCrad("红色", "A");
    Student liBai = new Student("李白", card);
    Librarian duFu = new Librarian("杜甫");
    Library library = new Library(duFu);
    library.borrowBook(liBai);
    }
}
