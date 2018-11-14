package com.yveschiong.personalrecordbook.common.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputEditText
import android.util.AttributeSet
import com.yveschiong.personalrecordbook.R


class ErrorEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputEditText(context, attrs) {

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
        val array = context.obtainStyledAttributes(attrs, R.styleable.ErrorEditText, 0, 0)

        errorText = array.getString(R.styleable.ErrorEditText_error)

        array.recycle()
    }

    override fun setError(error: CharSequence?) {
        super.setError(error)
        setErrorInternal(error)
    }

    override fun setError(error: CharSequence?, icon: Drawable?) {
        super.setError(error, icon)
        setErrorInternal(error)
    }

    private fun setErrorInternal(error: CharSequence?) {
        error?.let { errorText = it.toString() }
        showError = error != null
    }
}