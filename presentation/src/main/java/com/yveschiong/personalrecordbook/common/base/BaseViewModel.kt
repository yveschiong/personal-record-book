package com.yveschiong.personalrecordbook.common.base

import android.arch.lifecycle.ViewModel
import com.yveschiong.personalrecordbook.common.extensions.defaultThreads
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }

    protected fun Disposable.addToDisposables() {
        disposables.add(this)
    }

    protected fun <V, T : Single<V>> T.simpleSubscribe(func: (V) -> Unit) {
        simpleSubscribe(func, {})
    }

    protected fun <V, T : Single<V>> T.simpleSubscribe(func: (V) -> Unit, error: (Throwable) -> Unit) {
        defaultThreads().subscribe(func, error).addToDisposables()
    }

    protected fun <V, T : Single<V>> T.simpleSubscribe(subject: PublishSubject<V>) {
        defaultThreads().subscribe({
            subject.onNext(it)
        }, {
            subject.onError(it)
        }).addToDisposables()
    }
}
