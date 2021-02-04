package com.example.uas.model;

import java.util.ArrayList;

public class User {
    private static User user;
    private String name;
    private String nim;
    private static ArrayList<Cart> userscart;


    public static User getUser(){
        if(user == null){
            user = new User();
            userscart = new ArrayList<>();
        }

        return user;
    }
    public void resetUser(){
        user = new User();
        userscart = new ArrayList<>();
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public boolean addBooktoCart(Book book, int qty){
        for (Cart c: userscart) {
            if(c.book.id == book.id) {
                c.qty = qty;
            }
        }
        Cart c = new Cart(book, qty);
        return userscart.add(c);

    }

    public ArrayList<Cart> getUserscart() {
        return userscart;
    }
    public void deleteUsersCart(){
        userscart = new ArrayList<>();
    }
}
