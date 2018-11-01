package com.yveschiong.personalrecordbook.common.utils

import android.databinding.BindingAdapter
import android.widget.EditText
import com.yveschiong.personalrecordbook.common.utils.view.TextChange
import com.yveschiong.personalrecordbook.common.utils.view.TextChangeWatcher

@BindingAdapter("textChange")
fun EditText.textChange(textChange: TextChange) {
    addTextChangedListener(object : TextChangeWatcher() {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            textChange.onChange(charSequence.toString())
        }
    })
}
