package com.kittu.news;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by user on 23-03-2018.
 */

public class ApiClient {
    private static final String Key="97205db52ddf42e9a8324a9648083d28";
    private static final String BASE_URL="https://newsapi.org/v2/top-headlines/";
    private static final String country="in";
    public static Retrofit retrofit=null;

    public static NewsService newsService=null;

    public static NewsService getApiClient()
    {
        if(newsService==null)
        {

            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

            newsService=retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService
    {
        @GET("?country="+country+"&apiKey="+Key)
        Call<NewsList> getNews();

    }
}

