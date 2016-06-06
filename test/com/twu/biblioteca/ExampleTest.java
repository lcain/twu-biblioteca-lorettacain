package com.twu.biblioteca;


import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testName() {
        BibliotecaApp a = new BibliotecaApp("My Library");
        assertEquals(a.getName(), "My Library");
    }

    @Test
    public void testAddingBooks() {
        BibliotecaApp b = new BibliotecaApp("Main App");
        String[] testBooks = {"Alice in wonderland", "Harry Potter", "Whatever"};
        String[] testAuthours = {"Sherif", "Sherif", "Sherif"};
        for (int i = 0; i < testBooks.length; i++) {
            Book book = new Book(testBooks[i], testAuthours[i]);
            b.addBook(book);
        }

        for (int i = 0; i < testBooks.length; i++) {
            Assert.assertTrue(b.hasBook(testBooks[i]));
            Assert.assertEquals(b.getBook(testBooks[i]).getAuthor(), testAuthours[i]);
        }
    }
}
