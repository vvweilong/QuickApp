package com.oo.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.oo.baselogiclib.LoginManager

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.confirm_btn).setOnClickListener {
            val getTokenTask = GetTokenTask(GetTokenTask.LoginParamers("185613126900", "123456"))
            LoginManager.addTasks(getTokenTask)
        }
    }
}
