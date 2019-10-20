package com.hughod.app.ui.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.hughod.app.R

class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    companion object {
        fun launch(context: Context) =
            context.startActivity(Intent(context, SettingsActivity::class.java))
    }
}
