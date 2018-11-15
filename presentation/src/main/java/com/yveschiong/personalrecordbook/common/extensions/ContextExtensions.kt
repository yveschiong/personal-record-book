package com.yveschiong.personalrecordbook.common.extensions

import android.content.Context
import android.widget.Toast
import com.yveschiong.personalrecordbook.R

fun Context.toast(throwable: Throwable) {
    Toast.makeText(this, getString(R.string.generic_error_text), Toast.LENGTH_SHORT).show()
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}