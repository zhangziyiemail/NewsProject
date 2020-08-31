package com.example.github.newsproject.base

/**
 *   Created by zhangziyi on 8/26/20 16:55
 */

enum class State {
    RUNNING, SUCCESS, FAILED
}

data class NetworkState(
    val state: State,
    val msg: String? = null,
    val code: Int? = null
) {
    companion object {
        val LOADED = NetworkState(State.SUCCESS)
        val LOADING = NetworkState(State.RUNNING)
        fun error(msg: String?, code: Int = ERROR_CODE_NORM) = NetworkState(State.FAILED, msg ?: "unknown error", code)
    }
}