package com.twu.biblioteca;

import java.util.Date;

/**
 * Created by lorettacain on 6/06/2016.
 */
public class Book {

    private String title;
    private String Author;
    private int year;

    public Book(String title, String author) {
        this.title = title;
        Author = author;
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
