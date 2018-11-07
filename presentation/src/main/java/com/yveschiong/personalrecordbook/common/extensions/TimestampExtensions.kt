package com.yveschiong.personalrecordbook.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.getShortDate(): String {
    return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(this))
}

fun Long.getDate(): String {
    return SimpleDateFormat("EEE, MMMM d, yyyy", Locale.getDefault()).format(Date(this))
}

fun Long.getTime(): String {
    return SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date(this))
}