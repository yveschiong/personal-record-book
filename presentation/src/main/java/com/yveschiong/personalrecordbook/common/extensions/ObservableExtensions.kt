package com.yveschiong.personalrecordbook.common.extensions

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <V, T : Observable<V>> T.defaultThreads(): Observable<V> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <V, T : Single<V>> T.defaultThreads(): Single<V> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}