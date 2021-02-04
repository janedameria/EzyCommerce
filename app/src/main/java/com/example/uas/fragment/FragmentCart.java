package com.example.uas.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.adapter.BookCartAdapter;
import com.example.uas.model.BookList;
import com.example.uas.model.Cart;
import com.example.uas.model.User;
import com.google.android.material.resources.TextAppearance;

import java.util.ArrayList;
import java.util.Locale;

public class FragmentCart extends Fragment {

    private User user;
    private RecyclerView rv;
    private Button finishBtn;
    private BookCartAdapter adapter;
    private TextView subtotalNumberText, totalText, errorText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        user = User.getUser();
        ArrayList<Cart> usersCart = user.getUserscart();

        rv = view.findViewById(R.id.rvCart);
        errorText = view.findViewById(R.id.errorText);
        errorText.setText("");
        adapter = new BookCartAdapter(getContext());
        adapter.setUsersCart(usersCart);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        subtotalNumberText = view.findViewById(R.id.subtotalNumberText);
        totalText = view.findViewById(R.id.totalText);
        double sum = 0;

        for (Cart c: usersCart) {
            sum += c.getTotalPrice();
        }
        finishBtn = view.findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(usersCart.isEmpty()){
                  errorText.setText("There's no item in your cart. Please select first.");
               } else {
                  user.deleteUsersCart();
                  BookList.getBookList().reinitializeBook();
                  Intent myIntent = new Intent(getActivity(), MainFragment.class);
                  startActivity(myIntent);
              }
            }
        });

        subtotalNumberText.setText("$ " + String.format(Locale.US,"%.2f", sum));
        totalText.setText("$ " + String.format(Locale.US,"%.2f", sum));

        return view;
    }




}
