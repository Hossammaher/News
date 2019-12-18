package com.example.news.data;

import com.example.news.pojo.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {
    @GET("top-headlines")
    public Call<NewsResponse> getTopNews(@Query("country") String country,
                                         @Query("category") String category,
                                         @Query("apiKey") String api_key);



    @GET("everything")
    public Call<NewsResponse>getNewsSearch(@Query("q") String Keyword,
                                           @Query("sortBy") String sortBy ,
                                           @Query("apiKey") String api_key);
}