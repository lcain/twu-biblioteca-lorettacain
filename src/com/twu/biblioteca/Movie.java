package com.twu.biblioteca;

/**
 * Created by lorettacain on 8/06/2016.
 */
public class Movie {

    private String title;
    private String director;
    private int year;
    private int rating;
    private boolean checkedOut;

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Movie(String title, String directorName) {
        this.title = title;
        director = directorName;
        checkedOut = false;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String directorName) {
        director = directorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

//name, year, director and movie rating
