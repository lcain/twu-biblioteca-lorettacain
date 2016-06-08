package com.twu.biblioteca;

import java.util.*;

/**
 * Created by lorettacain on 8/06/2016.
 */
public class BibliotecaOptions {

    private String name;
    private Map<String, Book> books;
    private Map<String, Movie> movies;

    public BibliotecaOptions(String name) {
        this.name = name;
        this.books = new Hashtable<String, Book>();
        this.movies = new Hashtable<String, Movie>();
    }

    public String welcomeMessage(){
        return "Welcome to Biblioteca App Please choose an option: ";
    }

    public String mainMenuOptions(){
        return "\nShow all books (A)" +
                "\nCheck out a book (B)" +
                "\nReturn a book (C)" +
                "\nShow all movies (D)" +
                "\nCheck out a movie (E)" +
                "\nReturn a movie (F)" +
                "\nQuit (Q)\n";
    }

    public String exitMessage(){
        return "Thank you for using Biblioteca! Goodbye!";
    }

    public String selectAValidOption(){
        return "Please select a valid option!";
    }

    public String getName() {
        return name;
    }

    public Map getBooks() {
        return books;
    }

    public void addBook(Book book) {

        books.put(book.getTitle(), book);
    }

    public boolean hasBook(String bookname) {

        return books.containsKey(bookname);
    }

    public Book getBook(String bookName) {

        return books.get(bookName);
    }

    public Book checkout(String title) throws BookException{
        if (!books.containsKey(title)) {
            throw new BookException("This book is not in our library. Please try again.");
        }
        Book book = books.get(title);
        if (book.isCheckedOut()) {
            throw new BookException("Book is currently checked out!");
        }
        book.setCheckedOut(true);
        return book;
    }

    public Book returnBook(String title) throws BookException {
        if (!books.containsKey(title)) {
            throw new BookException("Incorrect entry, this book was not in our library. Try again.");
        }
        Book book = books.get(title);
        if (!book.isCheckedOut()) {
            throw new BookException("This book was not checked out.");
        }
        book.setCheckedOut(false);
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<Book>();
        for (String title : books.keySet()) {
            Book b = getBook(title);
            bookList.add(b);
        }
        return bookList;
    }

    public void showAllBooks() {
        System.out.printf("%-30s %-30s %-30s\n", "Title:", "Author:", "Published:");
        for (String title : books.keySet()) {
            Book b = getBook(title);
            if (b.isCheckedOut() == false){
                System.out.printf("%-30s %-30s %-30s\n", b.getTitle(), b.getAuthor(), b.getYear());
            }
        }
    }

    public Book createBook(String bookName, String authorName, int year){
        Book b = new Book(bookName, authorName);
        b.setYear(year);
        addBook(b);
        return b;
    }

    public Map getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {

        movies.put(movie.getTitle(), movie);
    }

    public boolean hasMovie(String moviename) {

        return movies.containsKey(moviename);
    }

    public Movie getMovie(String movieName) {

        return movies.get(movieName);
    }

    public Movie checkoutMovie(String title) throws BookException{
        if (!movies.containsKey(title)) {
            throw new BookException("This movie is not in our library. Please try again.");
        }
        Movie movie = movies.get(title);
        if (movie.isCheckedOut()) {
            throw new BookException("This movie is currently checked out!");
        }
        movie.setCheckedOut(true);
        return movie;
    }

    public Movie returnMovie(String title) throws BookException {
        if (!movies.containsKey(title)) {
            throw new BookException("Incorrect entry, this movie was not in our library. Try again.");
        }
        Movie movie = movies.get(title);
        if (!movie.isCheckedOut()) {
            throw new BookException("This movie was not checked out.");
        }
        movie.setCheckedOut(false);
        return movie;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        for (String title : movies.keySet()) {
            Movie m = getMovie(title);
            movieList.add(m);
        }
        return movieList;
    }

    public void showAllMovies() {
        System.out.printf("%-20s %-25s %-20s %-20s\n", "Title:","Director:", "Published:", "Rating:");
        for (String title : movies.keySet()) {
            Movie m = getMovie(title);
            if (m.isCheckedOut() == false){
                System.out.printf("%-20s %-25s %-20s %-20s\n", m.getTitle(), m.getDirector(), m.getYear(), m.getRating());
            }
        }
    }

    public Movie createMovie(String movieName, String directorName, int year, int rating){
        Movie m = new Movie(movieName, directorName);
        m.setYear(year);
        m.setRating(rating);
        addMovie(m);
        return m;
    }
}
