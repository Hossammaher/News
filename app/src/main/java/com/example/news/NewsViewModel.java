package com.example.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.NewsClient;
import com.example.news.pojo.Article;
import com.example.news.pojo.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    MutableLiveData<List<Article>> NewsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> News = new MutableLiveData<>();

    public void getTopNews(String category) {
        NewsClient.getINSTANCE().getTopNews(category).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                NewsMutableLiveData.setValue(response.body().getArticles());
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                News.setValue("ERROR");

            }
        });
    }

    public void getNewsSearch(String keyWord) {
        NewsClient.getINSTANCE().getNewsSearch(keyWord).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                NewsMutableLiveData.setValue(response.body().getArticles());
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                News.setValue("ERROR");

            }
        });
    }


}