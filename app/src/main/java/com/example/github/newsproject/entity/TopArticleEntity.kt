package com.example.github.newsproject.entity

import com.example.github.newsproject.data.db.HomeArticleDetail

/**
 *   Created by zhangziyi on 8/26/20 15:44
 */

data class TopArticleEntity(
    val `data`: List<HomeArticleDetail>,
    val errorCode: Int,
    val errorMsg: String
)