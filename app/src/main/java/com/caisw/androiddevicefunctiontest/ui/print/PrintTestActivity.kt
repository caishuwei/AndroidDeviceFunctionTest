package com.caisw.androiddevicefunctiontest.ui.print

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.print.PrintHelper
import com.caisw.androiddevicefunctiontest.R
import com.caisw.androiddevicefunctiontest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_print_test.*


class PrintTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print_test)

        v_print_picture.setOnClickListener {
            val photoPrinter = PrintHelper(this)
            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT)
            val bitmap = BitmapFactory.decodeResource(resources,
                    R.drawable.icon_add)
            photoPrinter.printBitmap("droids.jpg - test print", bitmap)
        }
    }

}