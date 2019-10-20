package com.hughod.app.ui.util

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes

fun View.setTextOrHide(@IdRes id: Int, name: String?) {
    val tv = this.findViewById<TextView>(id)

    if (name.isNullOrBlank()) {
        tv.visibility = View.GONE
    } else {
        tv.text = name
        tv.visibility = View.VISIBLE
    }
}
