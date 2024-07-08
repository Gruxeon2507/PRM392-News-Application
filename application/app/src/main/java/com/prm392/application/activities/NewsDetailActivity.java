package com.prm392.application.activities;
import android.content.Intent;
import android.os.Bundle;
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
}
