package com.caisw.androiddevicefunctiontest.ui.info

import android.app.Activity
import android.os.Build
import android.os.Bundle
import com.caisw.androiddevicefunctiontest.R
import com.caisw.androiddevicefunctiontest.ui.base.BaseActivity
import com.caisw.androiddevicefunctiontest.utils.ScreenInfo
import com.caisw.androiddevicefunctiontest.utils.Utils
import kotlinx.android.synthetic.main.activity_device_info.*

class DeviceInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)

        val sb = StringBuilder()
        sb.append(Utils.getDeviceInfo())
        sb.append("\n")
        sb.append("\n")
        sb.append("Android SDK:${Build.VERSION.SDK_INT}")
        sb.append("\n")
        sb.append("屏幕尺寸:（${ScreenInfo.WIDTH}，${ScreenInfo.HEIGHT}）")

        tv_device_info.setText(sb)
    }

}