package com.example.github.newsproject.utils

import android.content.Context
import androidx.core.content.edit

/**
 *   Created by zhangziyi on 6/23/20 23:33
 */
object SharePreferencesUtils {
    private const val SHARED_PREFERENCES_NAME = "com.base.share.preference"

    @JvmStatic
    fun getBoolean(context: Context, key: String , default : Boolean = false): Boolean{
        val  sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE)
        return sp.getBoolean(key,default);
    }

    @JvmStatic
    fun setBoolean (context: Context , key:String  , isfirst : Boolean){
        val sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.edit { putBoolean(key, isfirst) }
    }
}