package com.hughod.common.extensions

import java.text.DecimalFormat

fun Int.decimalFormat(): String = DecimalFormat("#,###,###").format(this)
