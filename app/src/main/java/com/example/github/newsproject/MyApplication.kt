package com.example.github.newsproject

import android.app.Application
import android.content.Context

/**
 *   Created by zhangziyi on 6/16/20 20:31
 */

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }

    companion object{
        lateinit var instance: Context
    }
}