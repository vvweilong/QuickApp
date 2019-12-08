package com.oo.uilibrary.ui.activitys

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.oo.uilibrary.R
import com.oo.uilibrary.interfaces.IBasePageInterface
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * 基类activity
 * */
abstract class BaseRefreshActivity : BaseActivity(), IBasePageInterface, OnRefreshListener,
    OnLoadMoreListener, OnMultiPurposeListener {

    private val dataContainer: FrameLayout by lazy { findViewById<FrameLayout>(R.id.main_container) }
    private val errorContainer: FrameLayout by lazy { findViewById<FrameLayout>(R.id.error_container) }
    private val loadingContainer: FrameLayout by lazy { findViewById<FrameLayout>(R.id.error_container) }
    private val refreshLayout: SmartRefreshLayout by lazy { findViewById<SmartRefreshLayout>(R.id.refresh_layout) }

    protected val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_layout)


        var dataLayoutId = if (getDataLayout() > 0) {
            getDataLayout()
        } else {
            R.layout.default_data_layout
        }

        var errorLayout = if (getErrorLayout() > 0) {
            getErrorLayout()
        } else {
            R.layout.default_error_layout
        }

        var loadingLayout = if (getLoadingLayout() > 0) {
            getLoadingLayout()
        } else {
            R.layout.default_loading_layout
        }

        val inflater = LayoutInflater.from(this)
        inflater.inflate(dataLayoutId, dataContainer, true)
        inflater.inflate(errorLayout, errorContainer, true)
        inflater.inflate(loadingLayout, loadingContainer, true)

        showLoading()


        refreshLayout.setEnableRefresh(canRefresh())
        refreshLayout.setEnableLoadMore(canLoadMore())
        refreshLayout.setOnMultiPurposeListener(this)
    }

    override fun onFooterMoving(
        footer: RefreshFooter?,
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        footerHeight: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onHeaderStartAnimator(
        header: RefreshHeader?,
        headerHeight: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onFooterReleased(footer: RefreshFooter?, footerHeight: Int, maxDragHeight: Int) {
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
    }

    override fun onHeaderMoving(
        header: RefreshHeader?,
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        headerHeight: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onFooterFinish(footer: RefreshFooter?, success: Boolean) {

    }

    override fun onFooterStartAnimator(
        footer: RefreshFooter?,
        footerHeight: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onHeaderReleased(header: RefreshHeader?, headerHeight: Int, maxDragHeight: Int) {
    }


    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if(timeout()>0){
            refreshLayout.layout.postDelayed({
                runOnUiThread {
                    loadingFinished("操作超时")
                }
            },timeout())
        }
        toLoadMore()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        if(timeout()>0){
            refreshLayout.layout.postDelayed({
                runOnUiThread {
                    refreshFinished("操作超时")
                }
            },timeout())
        }
        toRefresh()
    }

    override fun onHeaderFinish(header: RefreshHeader?, success: Boolean) {
    }

    /**
     * 操作超时
     * 比如在网络差的情况
     * 采用缓存数据 或者 ...
     * */
    open fun timeout(): Long {
        return 5000
    }

    open fun getErrorLayout(): Int {
        return -1
    }

    open fun getLoadingLayout(): Int {
        return -1
    }

    open fun getDataLayout(): Int {
        return -1
    }

    open fun getCustomToolbarLayout(): Int {
        return -1
    }

    /**
     * 当前页面是否可以 下拉进行刷新操作
     * */
    override fun canRefresh(): Boolean {
        return true
    }

    /**
     * 当前页面是否可以进行 上拉加载操作
     * */
    override fun canLoadMore(): Boolean {
        return true
    }

    override fun toRefresh() {

    }

    override fun toLoadMore() {
    }

    override fun refreshFinished(msg: String?) {
        refreshLayout.finishRefresh()
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
    @MainThread
    override fun loadingFinished(msg: String?) {
        refreshLayout.finishLoadMore()
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLoading() {
        if(timeout()>0){
          loadingContainer.postDelayed({
              hideLoading()
          },timeout())
        }
        loadingContainer.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingContainer.visibility = View.GONE
        dataContainer.visibility = View.VISIBLE
    }

    override fun showError(errorCode: Int, msg: String) {
        loadingContainer.visibility = View.GONE
        errorContainer.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorContainer.visibility = View.GONE
    }
}
