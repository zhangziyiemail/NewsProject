package com.example.github.newsproject.data


import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.github.newsproject.data.db.HomeArticleDetail

/**
 *   Created by zhangziyi on 8/28/20 16:50
 */
@Dao
interface HomeArticleCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheHomeArticles(articles : List<HomeArticleDetail>):List<Long>

    @Query("select * from home_article_cache")
    fun fetchAllCache() : DataSource.Factory<Int, HomeArticleDetail>

    @Query("delete from home_article_cache")
    fun clearHomeCache(): Int
}