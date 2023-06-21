package uz.gita.newsappcompose.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappcompose.data.response.NewsData

interface Api {

    @GET("api/1/news")
    suspend fun getLatestNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Response<NewsData>

}