package com.example.github.newsproject.ui

import android.content.Intent
import android.os.Bundle
import com.example.github.newsproject.R
import com.example.github.newsproject.base.BaseActivity
import com.example.github.newsproject.base.delayLaunch
import com.example.github.newsproject.databinding.ActivitySplashBinding

/**
 *   Created by zhangziyi on 6/16/20 20:32
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun initActivity(savedInstanceState: Bundle?) {
        delayLaunch(2000){
            block = {
                startActivity(Intent(this@SplashActivity, MianActivity::class.java))
                finish()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}