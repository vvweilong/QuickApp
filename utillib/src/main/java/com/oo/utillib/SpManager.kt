package com.oo.utillib

import android.content.Context
import android.content.SharedPreferences

/**
* create by 朱晓龙 2019/12/1 12:12 AM
 * 本地 sp 工具类
*/
object SpManager {

    const val UTILS_INFO="UTILS_INFO"



    lateinit var utilSp:SharedPreferences

    fun instialize(context: Context){
        utilSp=context.getSharedPreferences(UTILS_INFO,Context.MODE_PRIVATE)
    }

    fun putString(key:String,value:String){
        utilSp.edit().putString(key, value).apply()
    }
    fun getString(key:String):String?{
        return utilSp.getString(key,"")
    }

    fun putBoolean(key:String,value:Boolean){
        utilSp.edit().putBoolean(key, value).apply()
    }
    fun getBoolean(key:String):Boolean{
        return utilSp.getBoolean(key,false)
    }

}