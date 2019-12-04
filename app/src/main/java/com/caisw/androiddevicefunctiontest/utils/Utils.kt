package com.caisw.androiddevicefunctiontest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.caisw.androiddevicefunctiontest.app.MyApplication

class Utils {
    companion object {
        /**
         * 判断两个可空变量都不为空之后调用run
         */
        fun <T, K> runIfNotNull(arg1: T?, arg2: K?, run: (arg1: T, arg2: K) -> Unit) {
            if (arg1 != null && arg2 != null) {
                run(arg1, arg2)
            }
        }

        fun <T, K> getIfNotNull(arg1: T?, arg2: K?): Pair<T, K>? {
            if (arg1 != null && arg2 != null) {
                return Pair(arg1, arg2)
            }
            return null
        }

        /**
         * 判断当前网络是否可用
         */
        fun netWorkIsAvailable(): Boolean {
            val connectivityManager = MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            if (info != null && info.isConnected) {
                return true
            }
            return false
        }


        /**
         * 获取指定字段信息
         * @return
         */
        fun getDeviceInfo(): String {
            val sb = StringBuffer()
            sb.append("主板：" + Build.BOARD)
            sb.append("\n系统启动程序版本号：" + Build.BOOTLOADER)
            sb.append("\n系统定制商：" + Build.BRAND)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                sb.append("\ncpu指令集：")
                for ((i, abi) in Build.SUPPORTED_ABIS.withIndex()) {
                    sb.append("\n\t $abi")
                }
            } else {
                sb.append("\ncpu指令集：" + Build.CPU_ABI)
                sb.append("\ncpu指令集2：" + Build.CPU_ABI2)
            }
            sb.append("\n设置参数：" + Build.DEVICE)
            sb.append("\n显示屏参数：" + Build.DISPLAY)
            sb.append("\n无线电固件版本：" + Build.getRadioVersion())
            sb.append("\n硬件识别码：" + Build.FINGERPRINT)
            sb.append("\n硬件名称：" + Build.HARDWARE)
            sb.append("\nHOST:" + Build.HOST)
            sb.append("\n修订版本列表：" + Build.ID)
            sb.append("\n硬件制造商：" + Build.MANUFACTURER)
            sb.append("\n版本：" + Build.MODEL)
//            sb.append("\n硬件序列号："+Build.getSerial())//需要特殊权限 Manifest.permission.READ_PRIVILEGED_PHONE_STATE，这个权限不开放给第三方app。。。
            sb.append("\n手机制造商：" + Build.PRODUCT)
            sb.append("\n描述Build的标签：" + Build.TAGS)
            sb.append("\nTIME:" + Build.TIME)
            sb.append("\nbuilder类型：" + Build.TYPE)
            sb.append("\nUSER:" + Build.USER)
            return sb.toString()
        }

        /**
         * 通过反射获取所有的字段信息
         * @return
         */
        fun getDeviceInfo2(): String {
            val sbBuilder = StringBuilder()
            val fields = Build::class.java.getDeclaredFields()
            for (field in fields) {
                field.setAccessible(true);
                try {
                    sbBuilder.append("\n" + field.getName() + ":" + field.get(null).toString());
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace();
                } catch (e: IllegalAccessException) {
                    e.printStackTrace();
                }
            }
            return sbBuilder.toString();
        }


    }

}

