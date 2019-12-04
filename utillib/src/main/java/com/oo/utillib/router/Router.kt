package com.oo.utillib.router

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

/**
* create by 朱晓龙 2019/12/1 1:14 AM
 * 路由
*/
object Router {

    /**
     * 组件化  多个模块都可以进行注册
     * */
    val providerList = ArrayList<IRouterProvider>()
    fun regist(provider: IRouterProvider){
        if (providerList.contains(provider)) {
            return
        }
        providerList.add(provider)
    }

    fun unregist(provider: IRouterProvider){
        if (providerList.contains(provider)) {
            providerList.remove(provider)
        }
    }

    fun release(){
        providerList.clear()
    }

    val delayHandler = Handler(Looper.getMainLooper())


    private fun getTarget(routName:String):RoutData?{
        var routData:RoutData?=null
        for (iRouterProvider in providerList) {
            routData=iRouterProvider.getTarget(routName)
            if (routData != null) {
                break
            }
        }
        return routData
    }

    fun jumpTo(context: Context,rout:String,vararg args:String?= arrayOfNulls(1)){
        val target = getTarget(rout)
        if (target ==null) {
            return
        }
        if (target.isActivity) {
            context.startActivity(Intent(context,target.compontnet))
        }
    }
    fun jumpToDelay(context: Context,rout:String,delay:Long,vararg args:String?= arrayOfNulls(1)){
        delayHandler.postDelayed({
            jumpTo(context, rout)
        },delay)
    }

}