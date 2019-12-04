package com.caisw.androiddevicefunctiontest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity

open class BaseActivity : FragmentActivity() {

    private lateinit var contentViewContainer: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.VERTICAL
        ll.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        super.setContentView(ll)

        contentViewContainer = FrameLayout(this)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
        lp.weight = 1f
        ll.addView(contentViewContainer, lp)

        val button = Button(this)
        button.text = "Back"
        button.setOnClickListener {
            finish()
        }
        ll.addView(button, LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
    }


    override fun setContentView(layoutResID: Int) {
        val v = LayoutInflater.from(this).inflate(layoutResID, contentViewContainer, false)
        contentViewContainer.removeAllViews()
        contentViewContainer.addView(v)
    }
}