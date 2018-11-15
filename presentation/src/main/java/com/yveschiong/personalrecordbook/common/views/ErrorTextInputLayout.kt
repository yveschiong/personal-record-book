package com.yveschiong.personalrecordbook.common.views

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import com.yveschiong.personalrecordbook.R


class ErrorTextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs) {

    interface OnErrorShownListener {
        fun shown(shown: Boolean)
    }

    private var errorText: String? = null

    var errorShownListener: OnErrorShownListener? = null

    var showError: Boolean = false
        set(value) {
            if (field == value) {
                return
            }

            field = value

            error = if (value) {
                errorText
            } else {
                null
            }

            errorShownListener?.shown(value)
        }

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ErrorTextInputLayout, 0, 0)

        errorText = array.getString(R.styleable.ErrorTextInputLayout_error)

        array.recycle()
    }

    override fun setError(errorText: CharSequence?) {
        super.setError(errorText)

        error?.let { this.errorText = it.toString() }
        showError = error != null
    }
}