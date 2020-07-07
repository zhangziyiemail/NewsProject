package com.example.github.newsproject.ui.MainFragmnet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github.newsproject.entity.BannerData

/**
 *   Created by zhangziyi on 6/22/20 21:06
 */

class MainViewModel (private val  repository :MainRepository) : ViewModel(){
    val banners = MutableLiveData<List<BannerData>>()



}