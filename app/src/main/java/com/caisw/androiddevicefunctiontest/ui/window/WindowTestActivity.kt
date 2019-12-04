package com.caisw.androiddevicefunctiontest.ui.window

import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.caisw.androiddevicefunctiontest.R
import com.caisw.androiddevicefunctiontest.ui.base.BaseActivity
import com.caisw.androiddevicefunctiontest.utils.ScreenInfo
import com.caisw.androiddevicefunctiontest.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_window_test.*

class WindowTestActivity : BaseActivity() {
    private lateinit var windowView: View
    private val windowLayoutParams: WindowManager.LayoutParams

    init {
        windowLayoutParams = WindowManager.LayoutParams()
        //设置窗口类型， android 8.0以上只能设置TYPE_APPLICATION_OVERLAY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            windowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            windowLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        }
        //设置像素格式 但为什么用的是RGBA 而不是ARGB
        windowLayoutParams.format = PixelFormat.RGBA_8888
        //不允许触摸,这样触摸事件能传递到窗口之下
        //WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        //设置不取得焦点
        windowLayoutParams.flags = windowLayoutParams.flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        windowLayoutParams.gravity = Gravity.START or Gravity.TOP
        windowLayoutParams.x = 0
        windowLayoutParams.y = 0

        windowLayoutParams.width = ScreenInfo.WIDTH
        windowLayoutParams.height = ScreenInfo.HEIGHT
        windowLayoutParams.dimAmount = 0f

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window_test)

        windowView = View(this)
        windowView.setBackgroundColor(Color.RED)
        windowView.setOnClickListener {
            removeViewFormWindow()
        }

        v_add_float_window.setOnClickListener {
            SystemAlertWindowPermission.request(this, object : SystemAlertWindowPermission.OnRequestResultListener {
                override fun onResult(isGranted: Boolean) {
                    if (isGranted) {
                        addViewToWindow()
                    } else {
                        ToastUtils.showShort("悬浮窗权限被拒绝")
                    }
                }
            })
        }
    }

    private fun addViewToWindow() {
        if (windowView.parent == null) {
            windowManager.addView(windowView, windowLayoutParams)
        }
    }

    private fun removeViewFormWindow() {
        if (windowView.parent != null) {
            windowManager.removeView(windowView)
        }
    }

    override fun onDestroy() {
        removeViewFormWindow()
        super.onDestroy()
    }

}