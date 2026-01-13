package com.library.borrow;

/**
 * @author jh
 * @project word1
 * @time 2026/1/12
 */
public class Library {
    private Librarian librarian;
    public Library(Librarian librarian) {
        this.librarian = librarian;
    }
    public void borrowBook(Student student) {
        Book book = librarian.checkCard(student);
        System.out.println(student.getName() + "借到了" + book.getBookName());
    }
}
