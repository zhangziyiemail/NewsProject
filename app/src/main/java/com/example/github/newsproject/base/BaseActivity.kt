package com.example.github.newsproject.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 *   Created by zhangziyi on 6/16/20 20:33
 */

abstract class BaseActivity <VB : ViewDataBinding> : AppCompatActivity() , CoroutineScope by MainScope() {

    protected val mBinding : VB by lazy{
        DataBindingUtil.setContentView(this, getLayoutId())as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStackManager.addActivity(this)
        if (needTransparentStatus())  transparentStatusBar()
        mBinding.lifecycleOwner = this
        initActivity(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityStackManager.removeActivity(this)
        cancel()
        mBinding.unbind()
    }


    open fun transparentStatusBar(){
       window.decorView.systemUiVisibility =
           View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                   View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
    }

    protected  fun needTransparentStatus() : Boolean = false

    abstract fun initActivity(savedInstanceState: Bundle?)
    abstract fun getLayoutId():Int
}