package com.example.uas.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.uas.MainActivity;
import com.example.uas.R;
import com.example.uas.model.BookList;
import com.example.uas.model.User;
import com.example.uas.service.TheService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends AppCompatActivity {
    User user;

    BookList bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user = User.getUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        if(getIntent().hasExtra("cart")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCart()).commit();

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentBooklist()).commit();

        }




    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFragment = new FragmentBooklist();
                            break;
                        case R.id.cart:
                            selectedFragment = new FragmentCart();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        MenuItem menuItem = menu.getItem(0);
        menuItem.setTitle(user.getName());
          return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            stopService(new Intent(getBaseContext(), TheService.class));
            Intent intent1 = new Intent(this, MainActivity.class);
            this.startActivity(intent1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}