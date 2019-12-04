package com.heshihui.shishenghui.base.interfaces
/**
* 页面的基本操作
* */
interface IBasePageInterface {
    /*加载 刷新*/
    fun canRefresh():Boolean
    fun canLoadMore():Boolean
    fun toRefresh()
    fun toLoadMore()
    fun refreshFinished(msg:String?=null)
    fun loadingFinished(msg:String?=null)

    /*显示loading*/
    fun showLoading()
    fun hideLoading()
    /*数据异常*/
    /**
     * @param errorCode 错误类型
     * @param msg 错误信息
     * */
    fun showError(errorCode:Int,msg:String)
    fun hideError()
}