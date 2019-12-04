package com.caisw.androiddevicefunctiontest.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //用于解决使用太多第三方包导致方法数超出65k限制（一个dex包只能包含这么多个方法）使用分包
        //这里是兼容Android5.0以下的分包实现
        MultiDex.install(this)
    }

}