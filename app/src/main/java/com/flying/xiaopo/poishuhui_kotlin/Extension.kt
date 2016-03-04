package com.flying.xiaopo.poishuhui_kotlin

import android.content.Context
import android.content.res.Resources
import android.support.design.widget.Snackbar
import android.support.v4.view.PagerAdapter
import android.view.View
import android.widget.Toast

/**
 * Created by Flying SnowBean on 16-3-3.
 */
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
}

fun View.snackbar( message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(this, message, duration).show()
}
