package com.oo.uilibrary.ui.activitys

import com.oo.uilibrary.R

/**
 * 带有toolbar的baseactivity
 * */
abstract class BaseToolbarActivity : BaseRefreshActivity() {

    override fun getDataLayout(): Int {
        return R.layout.activity_base_toolbar
    }




}
