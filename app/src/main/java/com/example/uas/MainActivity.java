package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas.api.APIClient;
import com.example.uas.api.APIService;
import com.example.uas.api.BookResponse;
import com.example.uas.fragment.MainFragment;
import com.example.uas.model.Book;
import com.example.uas.model.BookList;
import com.example.uas.model.User;
import com.example.uas.service.TheService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText nim, nama;
    Button btn;
    User user;
    BookList bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        btn = findViewById(R.id.loginBtn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), TheService.class));

            }
        });


    }
    private void initialize(){
        user = User.getUser();
        bookList = BookList.getBookList();
        user.setName("Jennifer");
        user.setNim("2201853753");
    }


}