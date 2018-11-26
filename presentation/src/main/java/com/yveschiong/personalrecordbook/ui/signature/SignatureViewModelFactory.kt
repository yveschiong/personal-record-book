package com.yveschiong.personalrecordbook.ui.signature

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class SignatureViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignatureViewModel::class.java)) {
            return SignatureViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
