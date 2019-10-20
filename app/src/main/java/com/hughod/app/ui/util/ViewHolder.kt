package com.hughod.app.ui.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder<T>(
    parent: ViewGroup, @LayoutRes layoutRes: Int
) : RecyclerView.ViewHolder(inflate(parent, layoutRes)) {

    abstract fun bind(data: T, itemSelected: ((T) -> Unit)? = null)
}

private fun inflate(parent: ViewGroup, layoutRes: Int) =
    LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
