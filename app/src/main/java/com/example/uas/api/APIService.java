package com.example.uas.api;

import com.example.uas.model.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("search")
    Call<BookResponse> getBooks(
            @Query(value = "nim") String nim,
            @Query(value = "nama") String nama
    );

}
