package com.example.github.newsproject.entity

/**
 *   Created by zhangziyi on 6/23/20 00:13
 */
data class NewsEntityList(
    val `data`  : List<NewsEntity>,
    val errorCode : Int,
    val errorMsg : String
)

data class NewsEntity(
    val author : String,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String,
    val punlishedAt : String,
    val content :String
)