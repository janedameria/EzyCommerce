package com.example.uas.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.uas.MainActivity;
import com.example.uas.api.APIClient;
import com.example.uas.api.APIService;
import com.example.uas.api.BookResponse;
import com.example.uas.fragment.MainFragment;
import com.example.uas.model.Book;
import com.example.uas.model.BookList;
import com.example.uas.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TheService extends Service {
    User user;
    BookList bookList;

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        user = User.getUser();
        bookList = BookList.getBookList();
        Retrofit retrofit = APIClient.getRetrofit();
        APIService service = retrofit.create(APIService.class);
        Call<BookResponse> bookResponseCall = service.getBooks(user.getNim(), user.getName());

        bookResponseCall.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                List<Book> listBooks = response.body().products;
                bookList.setBookList(listBooks);
                Intent intent = new Intent(getBaseContext(), MainFragment.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                call.cancel();
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        User.getUser().resetUser();
//        BookList.getBookList().resetBookList();
        bookList.reinitializeBook();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);

    }
}
