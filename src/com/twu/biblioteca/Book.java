package com.twu.biblioteca;


/**
 * Created by lorettacain on 6/06/2016.
 */
public class Book {

    private String title;
    private String Author;
    private int year;
    private boolean checkedOut;
    private String borrowedBy;

    public void setBorrowedBy(String userID) {
        borrowedBy = userID;
    }
    public String getBorrowedBy(){
        return borrowedBy;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Book(String title, String author) {
        this.title = title;
        Author = author;
        checkedOut = false;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
