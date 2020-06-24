package com.example.github.newsproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 *   Created by zhangziyi on 6/24/20 00:27
 */
abstract  class BaseFragment<VB : ViewDataBinding> : Fragment() , CoroutineScope by MainScope() {

    protected var mBinding : VB?  = null

    protected lateinit var mNavController : NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        if (mBinding ==  null){
            mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
            actionsOnViewInflate()
        }


        mNavController = NavHostFragment.findNavController(this)

        return if (mBinding != null){
            mBinding!!.root.apply { (parent as? ViewGroup)?.removeView(this) }
        }else  super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.lifecycleOwner = this
        initFragment(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        mBinding?.unbind()
    }

    abstract fun initFragment(view: View, savedInstanceState: Bundle?)


    /**
     * income page connect network
     */
    open fun actionsOnViewInflate(){}

    abstract fun getLayoutId(): Int


    fun<T : ViewModel> getViewModel(clazz : Class<T>) : T =
        ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(clazz)

    fun<T : ViewModel> getSharedViewModel(clazz: Class<T>) : T =
        ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(clazz)

}