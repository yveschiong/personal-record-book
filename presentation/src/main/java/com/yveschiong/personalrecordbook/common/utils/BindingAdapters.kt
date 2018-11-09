package com.yveschiong.personalrecordbook.common.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import com.yveschiong.personalrecordbook.GlideApp
import com.yveschiong.personalrecordbook.common.utils.view.TextChange
import com.yveschiong.personalrecordbook.common.utils.view.TextChangeWatcher

object BindingAdapters {
    @BindingAdapter("textChange")
    @JvmStatic fun EditText.textChange(textChange: TextChange) {
        addTextChangedListener(object : TextChangeWatcher() {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                textChange.onChange(charSequence.toString())
            }
        })
    }

    @BindingAdapter("imageUrl", "placeholder")
    @JvmStatic fun loadImage(view: ImageView, url: String, placeholder: Drawable) {
        GlideApp.with(view).load(url).placeholder(placeholder).into(view)
    }
}
