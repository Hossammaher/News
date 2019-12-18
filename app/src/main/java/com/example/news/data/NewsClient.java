package com.example.news.data;



import com.example.news.pojo.NewsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "09c64dbf8d004e7b82035ac60095f0c5";
    private NewsInterface newsInterface;
    private static NewsClient INSTANCE;

    public NewsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        newsInterface = retrofit.create(NewsInterface.class);
    }

    public static NewsClient getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new NewsClient();
        }
        return INSTANCE;
    }

    public Call<NewsResponse> getTopNews(String category){
        return newsInterface.getTopNews("eg",category,API_KEY );
    }

    public Call<NewsResponse>getNewsSearch (String Keyword){

        return newsInterface.getNewsSearch(Keyword,"publishedAt",API_KEY);

    }

}
