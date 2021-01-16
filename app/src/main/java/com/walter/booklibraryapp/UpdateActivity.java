package com.walter.booklibraryapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etPages;
    String id, title, author, pages;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etTitle = findViewById(R.id.et_book_title_update);
        etAuthor = findViewById(R.id.et_book_author_update);
        etPages = findViewById(R.id.et_no_of_pages_update);
        btnUpdate = findViewById(R.id.btn_update_book);
        btnDelete = findViewById(R.id.btn_delete_book);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null ) ab.setTitle(title);

        btnUpdate.setOnClickListener(v -> {
            DatabaseHelper dbh = new DatabaseHelper(this);
            title = etTitle.getText().toString().trim();
            author = etAuthor.getText().toString().trim();
            pages = etPages.getText().toString().trim();
            dbh.updateData(id, title, author, pages);
        });

        btnDelete.setOnClickListener(v -> {
            confirmDialog();
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")
                && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            etTitle.setText(title);
            etAuthor.setText(author);
            etPages.setText(pages);
        } else Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            DatabaseHelper dbh = new DatabaseHelper(this);
            dbh.deleteRow(id);
            finish();
        });

        builder.setNegativeButton("No", (dialog, which) -> {

        });
        builder.create().show();
    }
}