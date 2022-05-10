package com.example.bibliotekfrontend;


public class Book {
    public String ID_book;
    public String book_title;
    public String book_qty;
    public String book_author;
    public String book_genre;
    public String book_year;
    public String book_URL;
    public String check_book;

    public Book(String ID_book, String book_title, String book_qty, String book_author, String book_genre, String book_year, String book_URL) {
        this.ID_book = ID_book;
        this.book_title = book_title;
        this.book_qty = book_qty;
        this.book_author = book_author;
        this.book_genre = book_genre;
        this.book_year = book_year;
        this.book_URL = book_URL;
    }

    public Book(String book_title, String book_qty, String book_author, String book_genre, String book_year, String book_url) {
        this.book_title = book_title;
        this.book_qty = book_qty;
        this.book_author = book_author;
        this.book_genre = book_genre;
        this.book_year = book_year;
        this.book_URL = book_url;
    }

    public Book(String check_book){
        this.check_book = check_book;
    }

    public String getCheck_book() {
        return check_book;
    }

    public void setCheck_book(String check_book) {
        this.check_book = check_book;
    }

    public String getID_book() {
        return ID_book;
    }

    public void setID_book(String ID_book) {
        this.ID_book = ID_book;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_qty() {
        return book_qty;
    }

    public void setBook_qty(String book_qty) {
        this.book_qty = book_qty;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_genre() {
        return book_genre;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    public String getBook_year() {
        return book_year;
    }

    public void setBook_year(String book_year) {
        this.book_year = book_year;
    }

    public String getBook_URL() {
        return book_URL;
    }

    public void setBook_URL(String book_URL) {
        this.book_URL = book_URL;
    }

    @Override
    public String toString() {
        return "Books{" +
                "ID_book=" + ID_book +
                ", book_title='" + book_title + '\'' +
                ", book_qty=" + book_qty +
                ", book_author='" + book_author + '\'' +
                ", book_genre='" + book_genre + '\'' +
                ", book_year='" + book_year + '\'' +
                ", book_URL='" + book_URL + '\'' +
                '}';
    }
}


