package com.walter.booklibraryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etNoOfPages;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.et_book_title);
        etAuthor = findViewById(R.id.et_book_author);
        etNoOfPages = findViewById(R.id.et_no_of_pages);
        btnAdd = findViewById(R.id.btn_add_book);
        btnAdd.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(AddActivity.this);
            dbHelper.addBook(etTitle.getText().toString().trim(),
                etAuthor.getText().toString().trim(),
                Integer.valueOf(etNoOfPages.getText().toString().trim()));
        });
    }
}