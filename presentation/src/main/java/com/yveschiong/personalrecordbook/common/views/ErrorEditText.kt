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

    var shown: Boolean = false
    var errorText: String? = null
    var errorShownListener: OnErrorShownListener? = null

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ErrorEditText, 0, 0)

        errorText = array.getString(R.styleable.ErrorEditText_error)

        array.recycle()
    }

    fun showError(show: Boolean) {
        shown = show
        error = if (show) {
            errorText
        } else {
            null
        }

        errorShownListener?.shown(show)
    }

    override fun setError(error: CharSequence?) {
        super.setError(error)

        shown = error != null
        errorShownListener?.shown(shown)
    }

    override fun setError(error: CharSequence?, icon: Drawable?) {
        super.setError(error, icon)

        shown = error != null
        errorShownListener?.shown(shown)
    }
}