package com.example.uas.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.adapter.BooksAdapter;
import com.example.uas.model.BookList;
import com.example.uas.model.User;

public class FragmentBooklist extends Fragment {

    private RecyclerView rv;
    private BooksAdapter adapter;
    private View listView;
    private User user;
    private BookList bookList;
    private LinearLayout categoriesLayout, categoriesLayout2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listView = inflater.inflate(R.layout.fragment_book_list, container, false);
        rv = listView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BooksAdapter(getContext());
        rv.setAdapter(adapter);
        bookList = BookList.getBookList();
        user = User.getUser();

        adapter.setBooks(bookList.getUsersBookList());
        categoriesLayout = listView.findViewById(R.id.CategoriesLayout);
        categoriesLayout2 = listView.findViewById(R.id.CategoriesLayout2);


        initalizeCategories();
        onStart();

        return listView;
    }

    private void initalizeCategories(){
        int count = 1;
        Button newbtn = new Button(getContext());
        newbtn.setText("All");
        newbtn.setBackgroundColor(Color.parseColor("#DB7556"));
        newbtn.setTextColor(Color.parseColor("#ffffff"));
        newbtn.setTextSize(11);
        newbtn.setTag("all");
        newbtn.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setBooks(bookList.getUsersBookList());
            }
        });
        categoriesLayout.addView(newbtn, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (String category : bookList.getCategories()) {
            count++;
            newbtn = new Button(getContext());
            newbtn.setText(category);
            newbtn.setTag(category);
            newbtn.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            newbtn.setBackgroundColor(Color.parseColor("#DB7556"));
            newbtn.setTextColor(Color.parseColor("#ffffff"));
            newbtn.setTextSize(11);

            newbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.setBooks(bookList.getOneCategory(category));
                }
            });

            if(count > 3){
                categoriesLayout2.addView(newbtn);
            } else {
                categoriesLayout.addView(newbtn);
            }
        }

    }

}
