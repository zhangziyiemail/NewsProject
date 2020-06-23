package com.example.github.newsproject.network

import android.util.TimeUtils
import com.example.github.newsproject.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *   Created by zhangziyi on 6/22/20 21:11
 */
object RetrofitManager {
    private var BASE_URL ="https://newsapi.org/v2/"

    val apiService : ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
         Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .client(genericOkClient())
             .build().create(ApiService::class.java)


    }

    private fun genericOkClient() :OkHttpClient{
        val httpLoggingInterceptor =  HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger{
                override fun log(message: String) {
                    if ((message.startsWith("{") && message.endsWith("}")) ||
                        (message.startsWith("[") && message.endsWith("]"))
                    )
                        LogUtils.json(message)
                    else
                        LogUtils.verbose(message)

                }
            }
        )
        httpLoggingInterceptor.level =HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(5_000L,TimeUnit.MILLISECONDS)
            .readTimeout(10_000L,TimeUnit.MILLISECONDS)
            .writeTimeout(30_000,TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

}