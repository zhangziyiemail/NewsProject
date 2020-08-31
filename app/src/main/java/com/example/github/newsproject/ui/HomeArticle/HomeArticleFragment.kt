package com.example.github.newsproject.ui.HomeArticle

import android.os.Bundle
import android.view.View
import com.example.github.newsproject.base.BaseFragment
import com.example.github.newsproject.databinding.FragmentHomeArticleBinding

/**
 *   Created by zhangziyi on 6/26/20 23:32
 */
class HomeArticleFragment : BaseFragment<FragmentHomeArticleBinding>(){

    private val mAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }
}
