package com.hughod.app.ui.util

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.hughod.app.R

fun FragmentActivity.showError(show: Boolean?) {
    findViewById<View>(R.id.error_view).visibility = if (show == true) View.VISIBLE else View.GONE
}
