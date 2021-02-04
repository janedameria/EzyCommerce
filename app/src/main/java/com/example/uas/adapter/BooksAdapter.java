package com.example.uas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uas.BookDetail;
import com.example.uas.R;
import com.example.uas.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    Context ctx;
    List<Book> listBooks;

    public BooksAdapter(Context c){
        this.listBooks = new ArrayList<Book>();
        this.ctx = c;
    }

    public void setBooks(List<Book> b){
        this.listBooks = b;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.ViewHolder holder, int position) {
        Book book = listBooks.get(position);
        holder.title.setText(book.name.toString());
        holder.price.setText("$ " + book.price.toString());
        Glide.with(ctx).load(book.img).into(holder.thumb);
        holder.linearLayoutofItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, BookDetail.class);
                intent.putExtra("book", book);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumb;
        TextView title, price;
        LinearLayout linearLayoutofItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumb  = itemView.findViewById(R.id.imagebook);
            title  = itemView.findViewById(R.id.titlebook);
            price  = itemView.findViewById(R.id.pricebook);
            linearLayoutofItem = itemView.findViewById(R.id.linearLayoutofItem);
        }
    }
}
