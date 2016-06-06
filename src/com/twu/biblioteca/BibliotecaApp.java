package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    private String name;
    private Map<String, Book> books;

    public BibliotecaApp(String name) {
        this.name = name;
        this.books = new Hashtable<String, Book>();
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

    public void showAllBooks() {
        for (String title : books.keySet()) {
            Book b = getBook(title);
            System.out.println("Book " + b.getTitle() + " was published by " + b.getAuthor()
                    + " in " + b.getYear());
        }
    }

    public void createBooks(String bookName, String authorName, int year){
        Book b = new Book(bookName, authorName);
        b.setYear(year);
        addBook(b);
    }

    public static void main(String[] args) {

        System.out.println("Welcome to Biblioteca App Please choose an option: ");
        System.out.print("Options: \nQuit (Q)" +
                "\nShow All books (S)\n");
        Scanner sc = new Scanner(System.in);
        BibliotecaApp app = new BibliotecaApp("Main App");

        app.createBooks("Harry Potter", "J.K. Rowling",1997);
        app.createBooks("The Hobbit", "J.R.R. Tolkien", 1937);
        app.createBooks("The Hunger Games", "Suzanne Collins", 2008);
        app.createBooks("The Little Prince", "Antoine de Saint-Exupery", 1943);
        app.createBooks("American Gods", "Neil Gaiman", 2001);
        app.createBooks("The Wee Free Men", "Terry Pratchett", 2003);
        app.createBooks("Reaper Man", "Terry Pratchett", 1991);
        app.createBooks("Small Gods", "Terry Pratchett", 1992);
        app.createBooks("Unseen Academicals", "Terry Pratchett", 2009);

        String choice = sc.nextLine().trim();
        while(!choice.equals("q")) {
            if (choice.equals(""))
                choice = sc.nextLine().trim();
            if (choice.equals("A")) {
                System.out.println("Please Enter Book Title:\n");
                String bookName = sc.nextLine();
                System.out.println("Please Enter Author Name:\n");
                String authorName = sc.nextLine();
                System.out.println("Please Enter publish year:\n");
                int year = sc.nextInt();
                Book b = new Book(bookName, authorName);
                b.setYear(year);
                app.addBook(b);
            } else if (choice.equals("S")) {
                app.showAllBooks();
            }
            System.out.println("Welcome to Biblioteca App Please choose an option: ");
            System.out.print("Options: \nQuit (Q)" +
                    "\nShow All books (S)\n");
            choice = sc.nextLine().trim();
        }

        System.out.println("Thanks for using my app");
    }
}
