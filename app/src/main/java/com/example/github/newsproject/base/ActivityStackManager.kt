package com.example.github.newsproject.base

import android.app.Activity

/**
 *   Created by zhangziyi on 6/17/20 22:06
 */
object ActivityStackManager {

    private val activities = mutableListOf<Activity>()

    @JvmStatic
    fun addActivity(activity: Activity)= activities.add(activity)

    @JvmStatic
    fun  removeActivity(activity: Activity){
        if (activities.contains(activity)){
            activities.remove(activity)
            activity.finish()
        }
    }

    @JvmStatic
    fun getTopActivity():Activity? =
        if (activities.isEmpty()) null else activities[activities.size-1]

    @JvmStatic
    fun finishAll() = activities.filter { it.isFinishing }.forEach{ it.finish() }


}