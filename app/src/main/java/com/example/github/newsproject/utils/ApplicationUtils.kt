package com.example.github.newsproject.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

/**
 *   Created by zhangziyi on 6/23/20 23:49
 */
object ApplicationUtils {


    @JvmStatic
    fun  getAppVersionName(context: Context):String{
        try {
            val packageInfo : PackageInfo = context.packageManager.getPackageInfo(context.packageName,0)
            return packageInfo.versionName
        }catch (e: PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        return ""
    }

}