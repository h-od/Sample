package com.hughod.app.ui.ext

import java.text.DecimalFormat

fun Int.decimalFormat(): String = DecimalFormat("#,###,###").format(this)
