package com.example.github.newsproject.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.github.newsproject.MyApplication
import com.example.github.newsproject.data.db.HomeArticleDetail
import com.example.github.newsproject.utils.LogUtils

/**
 *   Created by zhangziyi on 8/28/20 16:17
 */
@Database(entities = [HomeArticleDetail::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){
    abstract fun homeArticleCacheDao(): HomeArticleCacheDao
}

object MyDataBaseUtils {
    private const val DB_NAME = "my.db"

    private val instance : MyDatabase by lazy {
            Room.databaseBuilder(MyApplication.instance,MyDatabase::class.java,DB_NAME)
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        LogUtils.debug("create database $DB_NAME")
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        LogUtils.debug("open database $DB_NAME")
                    }
                }).build()
    }
    val homeArticleCacheDao = instance.homeArticleCacheDao()
}

