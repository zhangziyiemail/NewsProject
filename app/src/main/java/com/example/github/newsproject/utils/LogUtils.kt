package com.example.github.newsproject.utils

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

/**
 *   Created by zhangziyi on 6/23/20 11:20
 */

object LogUtils {
    private var className: String?  = null
    private var methodName : String? = null
    private var lineNumber : Int? = null

    private fun  isDebuggable() : Boolean = true

    private fun createLog(logMsg :String): String{
        return "$methodName($className:$lineNumber): $logMsg"
    }

    private fun getMethodName(throwable: Throwable){
        className = throwable.stackTrace[1].fileName
        methodName = throwable.stackTrace[1].methodName
        lineNumber = throwable.stackTrace[1].lineNumber
    }
    @JvmStatic
    fun json(json : String){
        if (json.isBlank()){
            info("blank json data")
            return
        }

        try {
            val message : String = when{
                json.startsWith("{") ->{
                    val jo = JSONObject(json)
                    jo.toString(4)
                }
                json.startsWith("[")->{
                    val  ja= JSONArray(json)
                    ja.toString(4)
                }
                else -> ""
            }
            getMethodName(Throwable())
            Log.i(className, createLog(message))
        }catch (e: Exception){
            error("${e.cause?.message}${System.getProperty("line.separator")}$json")
        }

    }

    @JvmStatic
    fun verbose(msg: Any?){
        if (!isDebuggable()) return
        getMethodName(Throwable())
        if(msg == null)
            Log.v(className , createLog("-----------empty----------"))
        else when (msg) {
            is Int, Long, Float, Double, Boolean -> Log.v(className, createLog("$msg"))
            is String -> Log.v(className, createLog(msg))
            else -> Log.v(className, createLog(msg.toString()))
        }
    }



    @JvmStatic
    fun  info(msg : Any?){
        if (!isDebuggable()) return
        getMethodName(Throwable())
        if (msg == null){
            Log.i(className, createLog("---------empty----------"))

        }else when(msg){
            is  Int, Long, Float, Double ,Boolean -> Log.i(className, createLog("$msg"))
            is  String -> Log.i(className, createLog(msg))
            else -> Log.i(className, createLog(msg.toString()))
        }
    }


}