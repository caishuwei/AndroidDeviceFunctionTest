package com.caisw.androiddevicefunctiontest.ui.network

import android.os.Build
import android.os.Bundle
import android.webkit.*
import com.caisw.androiddevicefunctiontest.R
import com.caisw.androiddevicefunctiontest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_network_test.*

class NetworkTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_test)

        setWebView(webView)
        webView.loadUrl("https://www.baidu.com/")
    }

    private fun setWebView(webview: WebView) {
        webview.setInitialScale(100)
        val webSettings = webview.settings
        webSettings.defaultFontSize = 16
        webview.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                //                LogUtils.e("ConsoleMessage", consoleMessage.message());
                return super.onConsoleMessage(consoleMessage)
            }
        }
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                webView.loadUrl(url)
                return true
            }
        }
        webSettings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webSettings.blockNetworkImage = false//使用图片占位图替代图片位置，不进行图片加载
        webSettings.loadsImagesAutomatically = true//自动下载图片
        webSettings.allowContentAccess = true//允许访问其他来源
        webSettings.allowFileAccess = true // 允许访问文件
        webSettings.useWideViewPort = true//采用网页推荐的ViewPort设置
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN//单列
        webSettings.pluginState = WebSettings.PluginState.ON// 可以使用插件
        webSettings.displayZoomControls = false
        webSettings.builtInZoomControls = true // 设置显示缩放按钮
        webSettings.setSupportZoom(false) // 关闭缩放
        webSettings.loadWithOverviewMode = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        //        webSettings.setTextZoom((int) (ScreenInfo.density * 100 + 0.5));//根据屏幕密度设置文本缩放
        if (Build.VERSION.SDK_INT < 8) {
        } else {
            webSettings.pluginState = WebSettings.PluginState.ON
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //设置对file类型的url的访问时，是否允许javaScript访问其他任何来源
            //为了安全应显示设置为false
            webSettings.allowFileAccessFromFileURLs = true
            webSettings.allowUniversalAccessFromFileURLs = true
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        //        //启用缓存
        //        webSettings.setAppCacheEnabled(true);
        //        webSettings.setAppCachePath(MyApplication.instance.getCacheDir().getPath());
        //        webSettings.setDatabaseEnabled(true);
    }

    override fun onDestroy() {
        webView.stopLoading()
        webView.destroy()
        super.onDestroy()
    }
}