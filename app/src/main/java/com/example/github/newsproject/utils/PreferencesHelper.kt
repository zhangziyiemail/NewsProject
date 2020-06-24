package com.example.github.newsproject.utils

import android.content.Context

/**
 *   Created by zhangziyi on 6/23/20 12:24
 */
object PreferencesHelper {

    private const val  STATE_KEY_FIRST_INT = "state_key_first_int"

    fun isFirstIn(context: Context) =
        SharePreferencesUtils.getBoolean(context, STATE_KEY_FIRST_INT,true)

    fun  saveFirstState (context: Context, isfirst : Boolean){
        SharePreferencesUtils.setBoolean(context, STATE_KEY_FIRST_INT, isfirst)
    }
}