package com.yveschiong.personalrecordbook.common.utils

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.github.gcacace.signaturepad.utils.SignaturePadBindingAdapter
import com.github.gcacace.signaturepad.views.SignaturePad
import com.yveschiong.personalrecordbook.GlideApp
import com.yveschiong.personalrecordbook.common.metadata.ImageMetadata
import com.yveschiong.personalrecordbook.common.metadata.isNullOrEmpty
import com.yveschiong.personalrecordbook.common.utils.view.TextChange
import com.yveschiong.personalrecordbook.common.utils.view.TextChangeWatcher
import com.yveschiong.personalrecordbook.common.views.ErrorEditText


object BindingAdapters {
    @BindingAdapter("textChange")
    @JvmStatic
    fun EditText.textChange(textChange: TextChange) {
        addTextChangedListener(object : TextChangeWatcher() {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                textChange.onChange(charSequence.toString())
            }
        })
    }

    @BindingAdapter("showError", "showErrorAttrChanged", requireAll = false)
    @JvmStatic
    fun ErrorEditText.setShowError(show: Boolean?, listener: InverseBindingListener) {
        if (showError == show) {
            return
        }

        errorShownListener = object : ErrorEditText.OnErrorShownListener {
            override fun shown(shown: Boolean) {
                listener.onChange()
            }
        }

        showError = show ?: false
    }

    @InverseBindingAdapter(attribute = "showError", event = "showErrorAttrChanged")
    @JvmStatic
    fun ErrorEditText.getShowError(): Boolean {
        return showError
    }

    @BindingAdapter("imageUrl", "metadata", "placeholder", requireAll = false)
    @JvmStatic
    fun ImageView.loadImage(url: String?, metadata: ImageMetadata?, placeholder: Drawable?) {
        if (url.isNullOrEmpty()) {
            return
        }

        var load = GlideApp.with(this).load(url)

        if (!metadata.isNullOrEmpty()) {
            metadata?.let {
                load = load.signature(ObjectKey(it))
            }
        }

        load.placeholder(placeholder).into(this)
    }

    // The built-in data-binding binding adapter for the signature pad doesn't seem to work
    // so this is the workaround for that. Have to look into why it doesn't just work directly
    @BindingAdapter("onSigned")
    @JvmStatic
    fun SignaturePad.setOnSignedListener(onSignedListener: SignaturePadBindingAdapter.OnSignedListener) {
        SignaturePadBindingAdapter.setOnSignedListener(this, null, onSignedListener, null)
    }

    @BindingAdapter("bitmapUrl", "metadata", requireAll = false)
    @JvmStatic
    fun SignaturePad.loadBitmap(url: String?, metadata: ImageMetadata?) {
        if (url.isNullOrEmpty()) {
            return
        }

        var load = GlideApp.with(this).asBitmap().load(url)

        if (!metadata.isNullOrEmpty()) {
            metadata?.let {
                load = load.signature(ObjectKey(it))
            }
        }

        load.into(object : CustomViewTarget<SignaturePad, Bitmap>(this) {
            override fun onLoadFailed(errorDrawable: Drawable?) {
                clear()
            }

            override fun onResourceCleared(placeholder: Drawable?) {
                clear()
            }

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                signatureBitmap = resource
            }
        })
    }

    @BindingAdapter("visible")
    @JvmStatic
    fun View.setVisible(visible: Boolean?) {
        visibility = if (visible != false) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @BindingAdapter("gone")
    @JvmStatic
    fun View.setGone(gone: Boolean?) {
        setVisible(gone?.not() ?: false)
    }
}
