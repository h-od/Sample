package com.hughod.app.ui.ext

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import com.bumptech.glide.Glide
import com.hughod.app.BuildConfig

fun View.setTextOrHide(@IdRes id: Int, text: String?) {
    val tv = this.findViewById<TextView>(id)

    if (text.isNullOrBlank()) {
        tv.visibility = View.GONE
    } else {
        tv.text = text
        tv.visibility = View.VISIBLE
    }
}

fun View.setClickListener(@IdRes idRes: Int, function: () -> Unit) =
    findViewById<View>(idRes).setOnClickListener { function() }

//todo extract Glide stuff to ImageManager/ ImageLoader
fun View.showImage(@IdRes idRes: Int, imagePath: String, imageSize: String = "w500") {
    if (imagePath.isNotBlank()) {
        Glide.with(this)
            .load("https://${BuildConfig.IMAGE_URL}$imageSize$imagePath")
            .into(findViewById(idRes))
    }
}
