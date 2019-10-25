package com.hughod.app.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.hughod.app.BuildConfig
import com.hughod.app.R
import com.hughod.app.ui.debug.DebugActivity
import com.hughod.app.ui.settings.SettingsActivity

class MoviesToolbar : FrameLayout {

    private var showSettings: Boolean = false
    private var showDebug: Boolean = false
    private var title: String? = null

    private val view = inflate(context, R.layout.view_toolbar, this)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context.obtainStyledAttributes(attrs, R.styleable.MoviesToolbar).apply {
            showSettings = getBoolean(R.styleable.MoviesToolbar_showSettings, false)
            showDebug = getBoolean(R.styleable.MoviesToolbar_showDebug, false)
            title = getString(R.styleable.MoviesToolbar_title)
            recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val titleTv = view.findViewById<TextView>(R.id.toolbar_text)
        titleTv.text = title ?: context.getString(R.string.app_name)

        val settings = view.findViewById<ImageView>(R.id.settings_icon)
        val debug = view.findViewById<ImageView>(R.id.debug_bug)

        super.post {
            settings.visibility = if (showSettings) {
                settings.setOnClickListener { SettingsActivity.launch(it.context) }
                VISIBLE
            } else GONE

            if (BuildConfig.DEBUG && showDebug) {
                debug.visibility = VISIBLE
                debug.setOnClickListener { DebugActivity.launch(it.context) }
            }
        }
    }
}
