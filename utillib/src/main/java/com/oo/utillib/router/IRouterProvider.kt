package com.oo.utillib.router

interface IRouterProvider {
    fun getTarget(rout:String):RoutData?
}