package com.example.github.newsproject.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *   Created by zhangziyi on 8/31/20 07:24
 */

open class BaseViewHolder<VB : ViewDataBinding>(val binding : VB)
    : RecyclerView.ViewHolder(binding.root)