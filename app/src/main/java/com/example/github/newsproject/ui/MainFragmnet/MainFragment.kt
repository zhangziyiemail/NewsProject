package com.example.github.newsproject.ui.MainFragmnet

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.github.newsproject.R
import com.example.github.newsproject.base.BaseFragment
import com.example.github.newsproject.base.BaseFragmentPagerAdapter
import com.example.github.newsproject.databinding.FragmentMainBinding

/**
 *   Created by zhangziyi on 6/22/20 20:40
 */

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mAdapter : BaseFragmentPagerAdapter by lazy {

        BaseFragmentPagerAdapter(
            childFragmentManager, arrayListOf(
                HomeArticleFragment(),
            )
        )
    }


    override fun getLayoutId(): Int {
         return  R.layout.fragment_main
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }


    fun openSettings(view: View){

    }

}