package com.prm392.application.api;

import com.prm392.application.models.Category;
import com.prm392.application.models.Comment;
import com.prm392.application.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Body;
import retrofit2.http.Path;

public interface ApiServices {
    @GET("api/news")
    Call<List<News>> getNews();

    @POST("api/news")
    Call<News> postNews(@Body News news);

    @PUT("api/news/{id}")
    Call<Void> updateNews(@Path("id") int id, @Body News news);

    @GET("news/{newsId}/comments")
    Call<List<Comment>> getComments(@Path("newsId") int newsId);

    @POST("news/{newsId}/comments")
    Call<Comment> postComment(@Body Comment comment);

    @POST("api/login")
    Call<Void> login(String username, String password);

    @GET("api/catergory")
    Call<Category> getCategory();
}
