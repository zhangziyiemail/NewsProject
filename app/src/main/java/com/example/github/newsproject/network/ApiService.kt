package com.example.github.newsproject.network

import com.example.github.newsproject.entity.HomeArticleEntity
import com.example.github.newsproject.entity.NewsEntity
import com.example.github.newsproject.entity.TopArticleEntity
import retrofit2.http.*

/**
 *   Created by zhangziyi on 6/22/20 21:49
 */
interface ApiService {

    @GET("/article/list/{page}/json")
    suspend fun homeArticles(@Path("page") page: Int): HomeArticleEntity

    @GET("/article/top/json")
    suspend fun topArticle(@Header("Cookie") cookie: String): TopArticleEntity

}