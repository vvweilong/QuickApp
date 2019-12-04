package com.heshihui.shishenghui.base.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heshihui.thirdpartuilib.R

/**
 * 带有toolbar的baseactivity
 * */
class BaseToolbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_toolbar)
    }
}
