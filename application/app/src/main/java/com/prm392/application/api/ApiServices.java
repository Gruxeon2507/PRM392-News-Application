package com.prm392.application.api;

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
}
