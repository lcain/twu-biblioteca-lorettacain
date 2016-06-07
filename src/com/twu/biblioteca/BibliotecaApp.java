package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    private String name;
    private Map<String, Book> books;
    
    public BibliotecaApp(String name) {
        this.name = name;
        this.books = new Hashtable<String, Book>();
    }

    public String welcomeMessage(){
        return "Welcome to Biblioteca App Please choose an option: ";
    }

    public String mainMenuOptions(){
        return "Options: \nQuit (Q)" +
                "\nShow All books (S)\n";
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
            throw new BookException("Book Not Found");
        }
        Book book = books.get(title);
        if (book.isCheckedOut()) {
            throw new BookException("Book was checked out");
        }
        book.setCheckedOut(true);
        return book;
    }

    public Book returnBook(String title) throws BookException {
        if (!books.containsKey(title)) {
            throw new BookException("Book Not Found");
        }
        Book book = books.get(title);
        if (!book.isCheckedOut()) {
            throw new BookException("Book was not checked out");
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
            System.out.printf("%-30s %-30s %-30s\n", b.getTitle(), b.getAuthor(), b.getYear());
        }
    }

    public Book createBooks(String bookName, String authorName, int year){
        Book b = new Book(bookName, authorName);
        b.setYear(year);
        addBook(b);
        return b;
    }

    public static void main(String[] args) {
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

        System.out.println(app.welcomeMessage());
        System.out.print(app.mainMenuOptions());

        Scanner sc = new Scanner(System.in);

        String choice = sc.nextLine().trim();
        while(!choice.equals("Q")) {
            if (choice.equals(""))
                choice = sc.nextLine().trim();
            else if (choice.equals("S")) {
                app.showAllBooks();
            } else if (choice.equals("C")) {
                try {
                    for (int i = 0; i < 2; i++) {
                        Book book = app.checkout("Unseen Academicals");
                        System.out.println(book.getTitle());
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(app.selectAValidOption());
            }
            System.out.print(app.mainMenuOptions());
            choice = sc.nextLine().trim();
        }
        System.out.println(app.exitMessage());

    }
}
