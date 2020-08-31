package com.example.github.newsproject.base

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 *   Created by zhangziyi on 8/31/20 07:21
 */

abstract class BasePagedListAdapter <T,VB : ViewDataBinding>(val callback : DiffUtil.ItemCallback<T>):
        PagedListAdapter<T,BaseViewHolder<VB>>(callback){

    private var itemListener : OnItemClickListener?  = null
    private var itemLongListener : OnItemLongClickListener? = null

    fun setOnItemLongListener(listener: OnItemLongClickListener){
        this.itemLongListener = listener
    }

    fun setOnItemListener(listener: OnItemClickListener){
        this.itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),getLayoutId(viewType), parent,false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val data = getItemData(position) ?: return
        setVariable(data,position,holder)
        holder.binding.executePendingBindings()
    }

    abstract fun getLayoutId(viewType : Int) : Int

    open fun getItemData(position: Int) : T? = getItem(position)

    abstract fun setVariable(data : T, position : Int , holder : BaseViewHolder<VB>)

}


