package com.example.github.newsproject.entity

/**
 *   Created by zhangziyi on 6/24/20 22:16
 */

data class BannerDataList(
    val `data`  : List<BannerData>,
    val errorCode : Int,
    val errorMsg : String
)


data class BannerData(
    val urlToImage: String,
    val title: String
)