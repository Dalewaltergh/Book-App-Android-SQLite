package com.walter.booklibraryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList bookId, bookTitle, bookAuthor, bookPages;
    Animation translateAnim;

    Adapter(Activity activity, Context context, ArrayList bookId, ArrayList bookTitle, ArrayList bookAuthor, ArrayList bookPages) {
        this.activity = activity;
        this.context = context;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        holder.tvId.setText(String.valueOf(bookId.get(position)));
        holder.tvTitle.setText(String.valueOf(bookTitle.get(position)));
        holder.tvAuthor.setText(String.valueOf(bookAuthor.get(position)));
        holder.tvPages.setText(String.valueOf(bookPages.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(bookId.get(position)));
            intent.putExtra("title", String.valueOf(bookTitle.get(position)));
            intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
            intent.putExtra("pages", String.valueOf(bookPages.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvTitle, tvAuthor, tvPages;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_book_id);
            tvTitle = itemView.findViewById(R.id.tv_book_title);
            tvAuthor = itemView.findViewById(R.id.tv_book_author);
            tvPages = itemView.findViewById(R.id.tv_book_pages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translateAnim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translateAnim);
        }
    }
}
