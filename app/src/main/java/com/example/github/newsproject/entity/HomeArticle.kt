package com.example.github.newsproject.entity

import com.example.github.newsproject.data.db.HomeArticleDetail

/**
 * @author kuky.
 * @description
 */
data class HomeArticleEntity(
    val `data`: ArticleData,
    val errorCode: Int,
    val errorMsg: String
)

data class ArticleData(
    val curPage: Int,
    val datas: List<HomeArticleDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)