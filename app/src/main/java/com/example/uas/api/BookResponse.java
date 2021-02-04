package com.example.uas.api;

import java.util.List;

import com.example.uas.model.Book;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookResponse {

    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("nim")
    @Expose
    public String nim;
    @SerializedName("nama")
    @Expose
    public String nama;
    @SerializedName("productId")
    @Expose
    public Object productId;
    @SerializedName("credits")
    @Expose
    public String credits;
    @SerializedName("products")
    @Expose
    public List<Book> products = null;

}