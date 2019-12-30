package com.oo.baselogiclib

import android.os.HandlerThread
import java.util.concurrent.LinkedBlockingQueue

object LoginManager : Thread.UncaughtExceptionHandler {


    //串行 逻辑任务队列
    private val logicTaskQueue = LinkedBlockingQueue<BaseLogicTask<BaseTaskParams,BaseTaskResult>>()

    //工作线程
    private val handlerThread = HandlerThread("logicTask")

    init {
        handlerThread.uncaughtExceptionHandler=this
        handlerThread.start()
    }

    fun addTasks(task:BaseLogicTask<BaseTaskParams,BaseTaskResult>){
        logicTaskQueue.add(task)
    }

    /**
     * 异常处理
     * */
    override fun uncaughtException(t: Thread, e: Throwable) {

    }
}