package com.prm392.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.application.R;
import com.prm392.application.adapters.CategoryAdapter;
import com.prm392.application.adapters.NewsAdapter;
import com.prm392.application.api.ApiClient;
import com.prm392.application.api.ApiServices;
import com.prm392.application.models.Comment;
import com.prm392.application.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView categoryRecyclerView;
    private NewsAdapter newsAdapter;
    private CategoryAdapter categoryAdapter;
    private ApiServices apiService;
    private List<News> newsList = new ArrayList<>();
    private List<String> categoryList = new ArrayList<>();
    private String selectedCategory = "All";
    private boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Get the login status from the intent
        isLoggedIn = getIntent().getBooleanExtra("isLoggedIn", false);

        // Set up the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("News Application");
        }

        // Set up new RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add divider between news items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Initialize the adapter with the activity context
        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);

        // Setup category RecyclerView
        categoryAdapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(String category) {
                selectedCategory = category;
                filterNewsByCategory();
            }
        });
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Load categories and news
        loadCategories();

        // Add default news items
        addDefaultNewsItem();
        addDefaultNewsItem();
        addDefaultNewsItem();

        apiService = ApiClient.getClient().create(ApiServices.class);

        fetchNews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterNewsByTitle(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterNewsByTitle(newText);
                return false;
            }
        });

        // Show login/logout option based on login status
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem logoutItem = menu.findItem(R.id.action_logout);
        if (isLoggedIn) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
        } else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        } else if (id == R.id.action_login) {
            login();
            return true;
        } else if (id == R.id.action_logout) {
            logout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void loadCategories() {
        // Add some sample categories
        categoryList.add("Tất Cả");
        categoryList.add("Dịch Vụ");
        categoryList.add("Thể Thao");
        categoryList.add("Sức Khỏe");
        categoryList.add("Kinh Tế");
        categoryList.add("Giải Trí");

        // Notify the adapter that the data has changed
        categoryAdapter.notifyDataSetChanged();
    }

    private void addDefaultNewsItem() {
        News defaultNews = new News();
        defaultNews.setNewsId(1);
        defaultNews.setTitle("Welcome to News App");
        defaultNews.setContent("This is a default news item to check if the layout works.");
        defaultNews.setImage("https://via.placeholder.com/150"); // Example image URL
        defaultNews.setAuthorId(1);
        defaultNews.setPublishedAt("2024-07-03T00:00:00");
        defaultNews.setUpdatedAt("2024-07-03T00:00:00");

        // Add sample comments
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment1.setNewsId(1);
        comment1.setUserId(1);
        comment1.setContent("This is a sample comment.");
        comments.add(comment1);

        Comment comment2 = new Comment();
        comment2.setId(2);
        comment2.setNewsId(1);
        comment2.setUserId(2);
        comment2.setContent("This is another sample comment.");
        comments.add(comment2);

        defaultNews.setComments(comments);

        newsList.add(defaultNews);
        newsAdapter.setNewsList(newsList);
    }

    private void fetchNews() {
        apiService.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsList = response.body();
                    newsAdapter.setNewsList(newsList);
                    addDefaultNewsItem();
                } else {
                    Toast.makeText(MainActivity.this, "No news available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load news: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "Failed to load news", t);
            }
        });
    }

    private void filterNewsByCategory() {
        List<News> filteredNewsList = new ArrayList<>();
        if (selectedCategory.equals("Tất Cả")) {
            filteredNewsList.addAll(newsList);
        } else {
            for (News news : newsList) {
                if (news.getTitle().toLowerCase().contains(selectedCategory.toLowerCase())) {
                    filteredNewsList.add(news);
                }
            }
        }
        newsAdapter.setNewsList(filteredNewsList);
    }

    private void filterNewsByTitle(String query) {
        List<News> filteredNewsList = new ArrayList<>();
        for (News news : newsList) {
            if (news.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredNewsList.add(news);
            }
        }
        newsAdapter.setNewsList(filteredNewsList);
    }

    private void login() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void logout() {
        isLoggedIn = false;
        invalidateOptionsMenu();
    }
}
