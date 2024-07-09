package com.prm392.application.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.prm392.application.R;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView contentTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Set up the action bar for back navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("News Application");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        imageView = findViewById(R.id.imageView);

        // Get data from the Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String image = intent.getStringExtra("image");

        // Set data to views
        titleTextView.setText(title);
        contentTextView.setText(content);
        if (image != null && !image.isEmpty()) {
            Picasso.get().load(image).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.placeholder);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the up (back) button
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
