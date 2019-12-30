package com.oo.baselogiclib

interface LogicTask<T1 :BaseTaskParams,T2:BaseTaskResult> {
    fun start(paramers:T1)
    fun processing()
    fun finished(result:T2)
}