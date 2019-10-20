package com.hughod.app.ui.debug

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.hughod.app.R

class DebugActivity : AppCompatActivity(R.layout.activity_debug) {

    companion object {
        fun launch(context: Context) =
            context.startActivity(Intent(context, DebugActivity::class.java))
    }
}
