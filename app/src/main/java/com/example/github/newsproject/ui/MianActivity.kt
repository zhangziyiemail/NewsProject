package com.example.github.newsproject.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import com.example.github.newsproject.R
import com.example.github.newsproject.base.BaseActivity
import com.example.github.newsproject.databinding.ActivityMainBinding
import com.example.github.newsproject.ui.MainFragmnet.MainFragment
import com.example.github.newsproject.utils.ApplicationUtils
import com.example.github.newsproject.utils.PreferencesHelper
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

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
        if (PreferencesHelper.isFirstIn(this)){
            alert(
                String.format(
                    "is first itme install",
                    ApplicationUtils.getAppVersionName(this)
                ), "welcomeback"
            ){
                isCancelable = false
                yesButton { PreferencesHelper.saveFirstState(this@MianActivity, false) }
            }.show()
        }
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.unregisterNetworkCallback(netStateCallback)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.first()
            .childFragmentManager.fragments.last().let {
                if (it is MainFragment){

                }
            }
        super.onBackPressed()

    }

    private fun checkState(){
        mBinding.netAvailable = availablelCount > 0
    }
}