package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaOptions app = new BibliotecaOptions("Main Options");

        app.createBook("Harry Potter", "J.K. Rowling",1997);
        app.createBook("The Hobbit", "J.R.R. Tolkien", 1937);
        app.createBook("The Hunger Games", "Suzanne Collins", 2008);
        app.createBook("The Little Prince", "Antoine de Saint-Exupery", 1943);
        app.createBook("American Gods", "Neil Gaiman", 2001);
        app.createBook("The Wee Free Men", "Terry Pratchett", 2003);
        app.createBook("Reaper Man", "Terry Pratchett", 1991);
        app.createBook("Small Gods", "Terry Pratchett", 1992);
        app.createBook("Unseen Academicals", "Terry Pratchett", 2009);

        app.createMovie("The Matrix", "Lilly & Lana Wachowski", 1999, 9);
        app.createMovie("Gladiator", "Ridley Scott", 2000, 9);
        app.createMovie("Totoro", "Hayao Miyazaki", 1988, 10);
        app.createMovie("The Jungle Book", "Jon Favreau", 2016, 5);
        app.createMovie("Fight Club", "David Fincher", 1999, 8);
        app.createMovie("Joy", "David O. Russell", 2015, 8);

        app.createUser("555-5555", "Loretta Cain", "password", "0424242424", "testing@gmail.com");
        app.createUser("444-4444", "Harry Potter", "password1", "0435353535", "testing2@gmail.com");

        System.out.println(app.welcomeMessage());


        Scanner sc = new Scanner(System.in);
        String libraryNumber = sc.nextLine();
        System.out.println("Please Enter Password:");
        String passWord = sc.nextLine();
        while (!passWord.equals(app.getPassword(libraryNumber))){
            System.out.println("Incorrect library number or password. Please try again.");
            System.out.println("Enter your library number:");
            libraryNumber = sc.nextLine();
            System.out.println("Enter your password:");
            passWord = sc.nextLine();
        }
        if (passWord.equals(app.getPassword(libraryNumber))) {
            System.out.print(app.mainMenuOptions());
            String choice = sc.nextLine().trim();
            while (!choice.equals("Q")) {
                if (choice.equals(""))
                    choice = sc.nextLine().trim();
                else if (choice.equals("A")) {
                    app.showAllBooks();
                } else if (choice.equals("B")) {
                    System.out.println("Type in the name of the book you wish to check out:");
                    try {
                        String bookName = sc.nextLine();
                        Book book = app.checkout(bookName, libraryNumber);
                        System.out.println(book.getTitle() + " has been checked out to you!");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (choice.equals("C")) {
                    System.out.println("Type in the name of the book you wish to return:");
                    try {
                        String bookName = sc.nextLine();
                        Book book = app.returnBook(bookName, libraryNumber);
                        System.out.println(book.getTitle() + " has been returned to the library!\nThank you!");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (choice.equals("D")) {
                    app.showAllMovies();
                } else if (choice.equals("E")) {
                    System.out.println("Type in the name of the movie you wish to check out:");
                    try {
                        String movieName = sc.nextLine();
                        Movie movie = app.checkoutMovie(movieName, libraryNumber);
                        System.out.println(movie.getTitle() + " has been checked out to you!");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (choice.equals("F")) {
                    System.out.println("Type in the name of the movie you wish to return:");
                    try {
                        String movieName = sc.nextLine();
                        Movie movie = app.returnMovie(movieName, libraryNumber);
                        System.out.println(movie.getTitle() + " has been returned to the library!\nThank you!");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (choice.equals("G")) {
                    app.showUserDetail(libraryNumber);
                } else {
                    System.out.println(app.selectAValidOption());
                }
                System.out.print(app.mainMenuOptions());
                choice = sc.nextLine().trim();
            }
        }

        System.out.println(app.exitMessage());

    }
}
