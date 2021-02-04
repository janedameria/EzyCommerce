package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uas.fragment.MainFragment;
import com.example.uas.model.Book;
import com.example.uas.model.BookList;
import com.example.uas.model.User;

public class BookDetail extends AppCompatActivity {
    ImageView image;
    TextView name, price, description;
    Book book;
    Button buyBtn;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        name = findViewById(R.id.bookNameTxt);
        image = findViewById(R.id.bookImageView);
        price = findViewById(R.id.bookPriceText);
        description = findViewById(R.id.bookDescTxt);
        buyBtn = findViewById(R.id.buyBtn);
        book = getIntent().getParcelableExtra("book");
        name.setText(book.name);
        Glide.with(this).load(book.img).into(image);
        user = User.getUser();
        price.setText("$ " + book.price.toString());
        description.setText(book.description);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.addBooktoCart(book, 1);
                Toast.makeText(getBaseContext(), "Book has been added to your cart!", Toast.LENGTH_SHORT).show();
                BookList.getBookList().removeBook(book.id);
                Intent intent = new Intent(BookDetail.this, MainFragment.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        MenuItem menuItem = menu.getItem(0);
        menuItem.setTitle(user.getName());
        return true;
    }
}