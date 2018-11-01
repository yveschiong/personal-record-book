package com.yveschiong.personalrecordbook.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.getDate(): String {
    return SimpleDateFormat("mm/dd/yyyy", Locale.getDefault()).format(Date(this))
}

fun Long.getTime(): String {
    return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(this))
}