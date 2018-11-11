package com.yveschiong.personalrecordbook.common.extensions

import android.graphics.drawable.Drawable

fun Drawable?.isNullOrEmpty(): Boolean {
    return this == null || (intrinsicWidth == 0 && intrinsicHeight == 0)
}