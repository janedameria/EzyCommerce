package com.example.uas.model;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private static  List<String> categories = new ArrayList<>();
    private static List<Book> bookList = new ArrayList<>();
    private static BookList instance;
    public void resetBookList(){

    }
    public static BookList getBookList(){
        if(instance == null){
            instance = new BookList();
        }
        return instance;
    }

    public void setBookList(List<Book> l){
        bookList = l;

        for (Book b: bookList) {
            if(!categories.contains(b.category)){
                categories.add(b.category);
            }
        }
    }

    public List<Book> getUsersBookList(){
        List<Book> tempList = new ArrayList<>();
        for (Book b: bookList ) {
            if(!b.inCart) tempList.add(b);

        }
        return tempList;
    }

    public List<String> getCategories(){
        return  categories;
    }

    public List<Book> getOneCategory(String category) {
        List<Book> tempList = new ArrayList<>();
        for (Book b : bookList) {
            if (b.category == category && b.inCart == false) {
                tempList.add(b);
            }
        }

        return tempList;
    }
    public void removeBook(int id){
        for (Book b:  bookList ) {
            if(b.id == id){
                b.inCart = true;
            }
        }
    }
    public void addBook(int id){
        for (Book b:  bookList ) {
            if(b.id == id){
                b.inCart = false;
            }
        }
    }
    public void reinitializeBook(){
        for (Book b:  bookList ) {
            if(b.inCart == true){
                b.inCart = false;
            }
        }
    }
}
