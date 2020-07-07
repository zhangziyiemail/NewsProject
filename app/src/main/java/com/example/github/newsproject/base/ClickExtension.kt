package com.example.github.newsproject.base

import android.view.MotionEvent
import android.view.View
import android.os.Handler

/**
 *   Created by zhangziyi on 7/1/20 21:30
 */


private const val  INTERVAL  = 300L

data class ClickCallback(
    var singleTap: () -> Unit = {},
    var doubleTap: () -> Unit = {}
)

class DoubleClickListener(init: ClickCallback.() -> Unit): View.OnTouchListener{
    private val callback  = ClickCallback().apply(init)
    private var count = 0
    private val handler = Handler()
    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            count++
            handler.postDelayed({
                if(count ==1)callback.singleTap()
                else if (count == 2) callback.doubleTap()
                handler.removeCallbacksAndMessages(0)
                count = 0
            } , INTERVAL)
        }
        return  false
    }

}