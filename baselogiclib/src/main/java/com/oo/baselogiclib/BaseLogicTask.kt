package com.oo.baselogiclib

abstract  class BaseLogicTask<T1:BaseTaskParams,T2:BaseTaskResult>:LogicTask<T1,T2> {
    enum class TaskType{
        //串行 并行
    }

}