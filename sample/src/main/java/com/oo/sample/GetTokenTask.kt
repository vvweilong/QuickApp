package com.oo.sample

import com.oo.baselogiclib.BaseLogicTask
import com.oo.baselogiclib.BaseTaskParams
import com.oo.baselogiclib.BaseTaskResult

class GetTokenTask(val paramers: LoginParamers): BaseLogicTask<GetTokenTask.LoginParamers,GetTokenTask.LoginResult>() {
    override fun start(paramers: LoginParamers) {

    }

    override fun processing() {
    }

    override fun finished(result: LoginResult) {
    }

    data class LoginParamers(val account:String,val psw:String):BaseTaskParams()
    data class LoginResult(val token:String):BaseTaskResult()



}