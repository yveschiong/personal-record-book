package com.yveschiong.personalrecordbook.common.extensions

import android.app.ProgressDialog
import android.support.v4.app.Fragment
import com.yveschiong.personalrecordbook.R

fun Fragment.showSavingSignatureProgressDialog(): ProgressDialog {
    return ProgressDialog.show(context, "", getString(R.string.saving_signature_loading_message), true)
}