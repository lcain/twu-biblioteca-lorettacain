package com.twu.biblioteca;


import org.junit.Assert;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTest {
    private String name;
    private Map<String, Book> books;

    @Test
    public void testWelcomeMessage() {
        BibliotecaApp a = new BibliotecaApp("My Library");
        assertEquals(a.welcomeMessage(), "Welcome to Biblioteca App Please choose an option: ");
    }

    @Test
    public void testName() {
        BibliotecaApp a = new BibliotecaApp("My Library");
        assertEquals(a.getName(), "My Library");
    }

    @Test
    public void testAddingBooks() {
        BibliotecaApp b = new BibliotecaApp("Main App");
        String[] testBooks = {"Alice in wonderland", "Harry Potter", "Whatever"};
        String[] testAuthours = {"Whoever", "That person", "Whatever Too"};
        for (int i = 0; i < testBooks.length; i++) {
            Book book = new Book(testBooks[i], testAuthours[i]);
            b.addBook(book);
        }

        for (int i = 0; i < testBooks.length; i++) {
            Assert.assertTrue(b.hasBook(testBooks[i]));
            Assert.assertEquals(b.getBook(testBooks[i]).getAuthor(), testAuthours[i]);
        }
    }

    private boolean searchArray(String[] strings, String string) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(string))
                return true;
        }
        return false;
    }

    private boolean searchList(List<Book> books, String title, String author) {
        for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
            Book next = iterator.next();
            if (next.getTitle().equals(title) && next.getAuthor().equals(author))
                return true;
        }
        return false;
    }

    @Test
    public void testShowAllBooks() {
        BibliotecaApp b = new BibliotecaApp("Main App");
        String[] testBooks = {"Alice in wonderland", "Harry Potter", "Whatever"};
        String[] testAuthours = {"Whoever", "That person", "Whatever Too"};
        for (int i = 0; i < testBooks.length; i++) {
            Book book = new Book(testBooks[i], testAuthours[i]);
            b.addBook(book);
        }
        List<Book> bookList = b.getAllBooks();
        Assert.assertEquals(bookList.size(), 3);
        int i = 0;
        for (Iterator<Book> iterator = bookList.iterator(); iterator.hasNext(); ) {
            Book book = iterator.next();
            assertTrue(searchArray(testBooks, book.getTitle()));
            assertTrue(searchArray(testAuthours, book.getAuthor()));
        }
//        assertEquals(b.showAllBooks(), );
    }

    @Test
    public void testCheckOut() {
        BibliotecaApp b = new BibliotecaApp("Main App");
        b.createBooks("Test Book", "Test Author", 2001);
        try {
            Book book = b.checkout("Test Book");
            Assert.assertNotNull(book);
            Assert.assertTrue(book.isCheckedOut());
        } catch (BookException e) {

        }
        try {
            Book book = b.checkout("Test Book");
        } catch (BookException e) {
            Assert.assertEquals(e.getMessage(), "Book was checked out");
        }
        try {
            Book book = b.checkout("Non Existent Book");
        } catch (BookException e) {
            Assert.assertEquals(e.getMessage(), "Book Not Found");
        }

    }

    @Test
    public void testReturnedBook() {
        BibliotecaApp b = new BibliotecaApp("Main App");
        b.createBooks("Test Book", "Test Author", 2001);
        try {
            b.checkout("Test Book");
            Book book = b.returnBook("Test Book");
            Assert.assertFalse(book.isCheckedOut());
        } catch (Exception e) {

        }
        try {
            Book book = b.returnBook("Test Book");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Book was not checked out");
        }
        try {
            Book book = b.returnBook("Non Existent Book");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Book Not Found");
        }

    }


//    @Test
//    public void testShowAllBooks() {
//        BibliotecaApp b = new BibliotecaApp("Main App");
//        books = new Hashtable<String, Book>();
//        String[] testBooks = {"Alice in wonderland", "Harry Potter", "Whatever"};
//        String[] testAuthours = {"Whoever", "That person", "Whatever Too"};
//        for (int i = 0; i < testBooks.length; i++) {
//            Book book = new Book(testBooks[i], testAuthours[i]);
//            b.addBook(book);
//        }
//        System.out.printf("%-30s %-30s %-30s\n", "Title:", "Author:", "Published:");
//        for (String title : books.keySet()) {
//            Book a = b.getBook(title);
//            System.out.printf("%-30s %-30s %-30s\n", a.getTitle(), a.getAuthor(), a.getYear());
//        }
////        assertEquals(b.showAllBooks(), );
//    }
}
