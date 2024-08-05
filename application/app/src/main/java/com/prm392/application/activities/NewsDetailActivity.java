package com.prm392.application.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392.application.R;
import com.prm392.application.adapters.CommentAdapter;
import com.prm392.application.api.ApiClient;
import com.prm392.application.api.ApiServices;
import com.prm392.application.models.Comment;
import com.prm392.application.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private TextView newsTitle;
    private TextView newsContent;
    private ImageView newsImage;
    private TextView newsPublishedAt;
    private RecyclerView commentsRecyclerView;
    private EditText commentEditText;
    private Button postCommentButton;
    private CommentAdapter commentAdapter;
    private ApiServices apiService;
    private List<Comment> commentList = new ArrayList<>();
    private boolean isLoggedIn;
    private int newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsTitle = findViewById(R.id.newsTitle);
        newsContent = findViewById(R.id.newsContent);
        newsImage = findViewById(R.id.newsImage);
        newsPublishedAt = findViewById(R.id.newsPublishedAt);
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);
        commentEditText = findViewById(R.id.commentEditText);
        postCommentButton = findViewById(R.id.postCommentButton);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("News Application");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        isLoggedIn = getIntent().getBooleanExtra("isLoggedIn", false);

        if (!isLoggedIn) {
            commentEditText.setVisibility(View.GONE);
            postCommentButton.setVisibility(View.GONE);
        }

        newsId = getIntent().getIntExtra("newsId", 0);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String imageUrl = getIntent().getStringExtra("image");
        String publishedAt = getIntent().getStringExtra("publishedAt");

        newsTitle.setText(title);
        newsContent.setText(content);
        Glide.with(this).load(imageUrl).into(newsImage);
        newsPublishedAt.setText(publishedAt);

        commentAdapter = new CommentAdapter(commentList);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentsRecyclerView.setAdapter(commentAdapter);

        apiService = ApiClient.getClient().create(ApiServices.class);

        loadComments();

        postCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoggedIn) {
                    postComment();
                } else {
                    Toast.makeText(NewsDetailActivity.this, "Please log in to post a comment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadComments() {
        apiService.getComments(newsId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    commentList = response.body();
                    commentAdapter.setCommentList(commentList);
                } else {
                    Toast.makeText(NewsDetailActivity.this, "No comments available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, "Failed to load comments: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postComment() {
        String commentText = commentEditText.getText().toString();
        if (TextUtils.isEmpty(commentText)) {
            Toast.makeText(this, "Comment cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Comment comment = new Comment();
        comment.setNewsId(newsId);
        comment.setUserId(1); // Replace with the actual user ID from your login mechanism
        comment.setContent(commentText);

        apiService.postComment(comment).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful() && response.body() != null) {
                    commentList.add(response.body());
                    commentAdapter.setCommentList(commentList);
                    commentEditText.setText("");
                    Toast.makeText(NewsDetailActivity.this, "Comment posted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewsDetailActivity.this, "Failed to post comment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, "Failed to post comment: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
