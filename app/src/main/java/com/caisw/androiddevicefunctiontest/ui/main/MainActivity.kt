package com.caisw.androiddevicefunctiontest.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_HOME_SETTINGS
import com.caisw.androiddevicefunctiontest.R
import com.caisw.androiddevicefunctiontest.ui.base.BaseActivity
import com.caisw.androiddevicefunctiontest.ui.info.DeviceInfoActivity
import com.caisw.androiddevicefunctiontest.ui.network.NetworkTestActivity
import com.caisw.androiddevicefunctiontest.ui.print.PrintTestActivity
import com.caisw.androiddevicefunctiontest.ui.window.WindowTestActivity
import com.caisw.androiddevicefunctiontest.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        v_setting_home.setOnClickListener {
            try {
                startActivity(Intent(ACTION_HOME_SETTINGS))
            } catch (e: Exception) {
                ToastUtils.showShort("无法跳转设置桌面")
            }
        }
        v_device_info.setOnClickListener {
            startActivity(DeviceInfoActivity::class.java)
        }
        v_html_load.setOnClickListener {
            startActivity(NetworkTestActivity::class.java)
        }
        v_add_float_window.setOnClickListener {
            startActivity(WindowTestActivity::class.java)
        }
        v_print.setOnClickListener {
            startActivity(PrintTestActivity::class.java)
        }
    }

    fun <T> startActivity(c: Class<T>) {
        startActivity(Intent(this, c))
    }
}