package com.example.bibliotekfrontend;

public class BorrowedBook {

    String customerPNR;
    String ID_book;
    String book_title;
    String book_author;
    String book_year;


    String return_date;

    public BorrowedBook(String customerPNR, String ID_book, String book_title, String book_author, String book_year, String return_date) {
        this.customerPNR = customerPNR;
        this.ID_book = ID_book;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_year = book_year;
        this.return_date = return_date;
    }

    public String getCustomerPNR() {
        return customerPNR;
    }

    public void setCustomerPNR(String customerPNR) {
        this.customerPNR = customerPNR;
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

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_year() {
        return book_year;
    }

    public void setBook_year(String book_year) {
        this.book_year = book_year;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
                "customerPNR='" + customerPNR + '\'' +
                ", ID_book='" + ID_book + '\'' +
                ", book_title='" + book_title + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_year='" + book_year + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }



}
