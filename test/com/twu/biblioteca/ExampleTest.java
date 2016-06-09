package com.twu.biblioteca;


import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExampleTest {

    @Test
    public void testWelcomeMessage() {
        BibliotecaOptions a = new BibliotecaOptions("My Library");
        assertEquals(a.welcomeMessage(), "Welcome to Biblioteca App!\nPlease log in to proceed. \nEnter your library number:");
    }

    @Test
    public void testName() {
        BibliotecaOptions a = new BibliotecaOptions("My Library");
        assertEquals(a.getName(), "My Library");
    }

    @Test
    public void testAddingBooks() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
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
        BibliotecaOptions b = new BibliotecaOptions("Main App");
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

    }

    @Test
    public void testCheckOutBook() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
        b.createBook("Test Book", "Test Author", 2001);
        b.createUser("555-5555", "Loretta Cain", "password", "0424242424", "testing@gmail.com");
        try {
            Book book = b.checkout("Test Book", "555-5555");
            Assert.assertNotNull(book);
            Assert.assertTrue(book.isCheckedOut());
            Assert.assertEquals("555-5555", book.getBorrowedBy());
        } catch (BookException e) {

        }
        try {
            Book book = b.checkout("Test Book", "555-5555");
        } catch (BookException e) {
            Assert.assertEquals(e.getMessage(), "Book is currently checked out!");
        }
        try {
            Book book = b.checkout("Non Existent Book", "555-5555");
        } catch (BookException e) {
            Assert.assertEquals(e.getMessage(), "This book is not in our library. Please try again.");
        }

    }

    @Test
    public void testAddingMovies() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
        String[] testMovieTitles = {"Alice in wonderland", "Harry Potter", "Whatever"};
        String[] testDirectors = {"Whoever", "That person", "Whatever Too"};
        for (int i = 0; i < testMovieTitles.length; i++) {
            Movie movie = new Movie(testMovieTitles[i], testDirectors[i]);
            b.addMovie(movie);
            assertNotNull(movie);
        }

        for (int i = 0; i < testMovieTitles.length; i++) {
            Assert.assertTrue(b.hasMovie(testMovieTitles[i]));
            Assert.assertEquals(b.getMovie(testMovieTitles[i]).getDirector(), testDirectors[i]);
        }
    }

    @Test
    public void testReturnedBook() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
        b.createBook("Test Book", "Test Author", 2001);
        try {
            b.checkout("Test Book", "555-5555");
            Book book = b.returnBook("Test Book", "555-5555");
            Assert.assertFalse(book.isCheckedOut());
            Assert.assertEquals("", book.getBorrowedBy());
        } catch (Exception e) {

        }
        try {
            Book book = b.returnBook("Test Book", "555-5555");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "This book was not checked out.");
        }
        try {
            Book book = b.returnBook("Non Existent Book", "555-5555");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Incorrect entry, this book was not in our library. Try again.");
        }
    }

    @Test
    public void testShowAllMovies() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
        String[] testMovies = {"Alice in wonderland", "Harry Potter", "Whatever"};
        String[] testDirectors = {"Whoever", "That person", "Whatever Too"};
        for (int i = 0; i < testMovies.length; i++) {
            Movie movie = new Movie(testMovies[i], testDirectors[i]);
            b.addMovie(movie);
        }
        List<Movie> movieList = b.getAllMovies();
        Assert.assertEquals(movieList.size(), 3);
        int i = 0;
        for (Iterator<Movie> iterator = movieList.iterator(); iterator.hasNext(); ) {
            Movie movie = iterator.next();
            assertTrue(searchArray(testMovies, movie.getTitle()));
            assertTrue(searchArray(testDirectors, movie.getDirector()));
        }

    }

    @Test
    public void testCheckOutMovie() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
        b.createMovie("Test Movie", "Test Director", 2001, 10);
        try {
            Movie movie = b.checkoutMovie("Test Movie", "555-5555");
            Assert.assertNotNull(movie);
            Assert.assertTrue(movie.isCheckedOut());
            Assert.assertEquals("555-5555", movie.getBorrowedBy());
        } catch (BookException e) {

        }
        try {
            Movie movie = b.checkoutMovie("Test Movie", "555-5555");
        } catch (BookException e) {
            Assert.assertEquals(e.getMessage(), "This movie is currently checked out!");
        }
        try {
            Movie movie = b.checkoutMovie("Non Existent Movie", "555-5555");
        } catch (BookException e) {
            Assert.assertEquals(e.getMessage(), "This movie is not in our library. Please try again.");
        }

    }

    @Test
    public void testReturnedMovie() {
        BibliotecaOptions b = new BibliotecaOptions("Main App");
        b.createMovie("Test Movie", "Test Director", 2001, 10);
        try {
            b.checkoutMovie("Test Movie", "555-5555");
            Movie movie = b.returnMovie("Test Movie", "555-5555");
            Assert.assertFalse(movie.isCheckedOut());
            Assert.assertEquals("", movie.getBorrowedBy());
        } catch (Exception e) {

        }
        try {
            Movie movie = b.returnMovie("Test Movie", "555-5555");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "This movie was not checked out.");
        }
        try {
            Movie movie = b.returnMovie("Non Existent Movie", "555-5555");
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Incorrect entry, this movie was not in our library. Try again.");
        }
    }

    @Test
    public void testCreateNewUsers() {

        BibliotecaOptions b = new BibliotecaOptions("Main App");
        String[] testLibraryNumber = {"111-1111", "222-2222", "333-3333"};
        String[] testUserName = {"Whoever", "That person", "Whatever Too"};
        String[] testPassword = {"password", "passwording", "passwordz"};
        String[] testPhoneNumber = {"0404040404", "0404040404", "0404040404"};
        String[] testEmailAddress = {"testing@testing.com", "testing@thetest.com", "thetest@testing.testing"};
        for (int i = 0; i < testLibraryNumber.length; i++) {
            User user = new User(testLibraryNumber[i], testUserName[i], testPassword[i], testPhoneNumber[i], testEmailAddress[i]);
            b.addUser(user);
            assertNotNull(user);
        }

        for (int i = 0; i < testLibraryNumber.length; i++) {
            Assert.assertEquals(b.getPassword(testLibraryNumber[i]), testPassword[i]);
        }
    }

}
