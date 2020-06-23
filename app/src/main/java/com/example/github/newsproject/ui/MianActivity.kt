package com.example.github.newsproject.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import com.example.github.newsproject.base.BaseActivity
import com.example.github.newsproject.databinding.ActivityMainBinding

/**
 *   Created by zhangziyi on 6/22/20 17:59
 */
class MianActivity : BaseActivity<ActivityMainBinding>(){
    private var availablelCount =0

    private val manager : ConnectivityManager by lazy{
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val request : NetworkRequest by lazy{
        NetworkRequest.Builder().build()
    }

    private val netStateCallback : ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                availablelCount++
                checkState()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                availablelCount--
                checkState()
            }
        }
    }
    override fun initActivity(savedInstanceState: Bundle?) {
        manager.registerNetworkCallback(
            request,netStateCallback
        )
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }



    private fun checkState(){
        mBinding.netAvailable = availablelCount > 0
    }
}