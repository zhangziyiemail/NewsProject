package com.example.github.newsproject.network

import com.example.github.newsproject.entity.NewsEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 *   Created by zhangziyi on 6/22/20 21:49
 */
interface ApiService {

    @GET("everything")
    suspend fun homeArticles(@QueryMap map: Map<String,String>): NewsEntity

}