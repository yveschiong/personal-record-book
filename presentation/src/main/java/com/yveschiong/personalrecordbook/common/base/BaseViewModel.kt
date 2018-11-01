package com.yveschiong.personalrecordbook.common.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }

    protected fun Disposable.addToDisposables() {
        disposables.add(this)
    }
}
