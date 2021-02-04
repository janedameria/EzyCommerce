package com.example.uas.model;

public class Cart {
    public Book book;
    public int qty;
    public double totalPrice;

    public Cart(Book b, int qty){
        this.book = b;
        this.qty = qty;
        this.totalPrice = book.price * this.qty;
    }
    public void addQty(int qty){
        this.qty = qty;
        this.totalPrice = book.price * this.qty;
    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
