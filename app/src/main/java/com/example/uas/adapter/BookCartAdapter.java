package com.example.uas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uas.fragment.MainFragment;
import com.example.uas.R;
import com.example.uas.model.Book;
import com.example.uas.model.BookList;
import com.example.uas.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class BookCartAdapter extends RecyclerView.Adapter<BookCartAdapter.ViewHolder> {
    Context ctx;
    List<Cart> usersCart;
    BookList bookList;
    public BookCartAdapter(Context c){
        this.usersCart = new ArrayList<Cart>();
        this.ctx = c;
        bookList = BookList.getBookList();
    }

    public void setUsersCart(List<Cart> b){
        this.usersCart = b;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.book_item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookCartAdapter.ViewHolder holder, int position) {
        Cart cart = usersCart.get(position);
        Book book = cart.getBook();
        holder.title.setText(book.name.toString());
        holder.price.setText("$ " + book.price.toString() + " x " + cart.qty + " = $" + cart.totalPrice);

        Glide.with(ctx).load(book.img).into(holder.thumb);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersCart.remove(cart);
                bookList.addBook(book.id);
                Intent intent = new Intent(ctx, MainFragment.class);

                intent.putExtra("cart", "REFRESH_DATA");
                ctx.startActivity(intent);
            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qtyInput = holder.qty.getText().toString();
                if(!qtyInput.isEmpty()){
                    cart.addQty(Integer.parseInt(qtyInput));
                    Intent intent = new Intent(ctx, MainFragment.class);
                    intent.putExtra("cart", "REFRESH_DATA");
                    ctx.startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Please input number.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return this.usersCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumb;
        TextView title, price;
        Button addBtn;
        ImageButton deleteBtn;
        EditText qty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumb  = itemView.findViewById(R.id.imagebook);
            title  = itemView.findViewById(R.id.titlebook);
            price  = itemView.findViewById(R.id.pricebook);
            addBtn = itemView.findViewById(R.id.addBtn);
            qty = itemView.findViewById(R.id.qtyInput);

            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
