package com.yveschiong.domain.common

import io.reactivex.Observable

import java.util.ArrayList

abstract class Mapper<E, T> {
    abstract fun mapFrom(from: E): T

    fun mapFrom(from: List<E>): List<T> {
        val list = ArrayList<T>()
        for (item in from) {
            list.add(mapFrom(item))
        }

        return list
    }

    fun mapFromObservable(from: E): Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun mapFromObservable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { mapFrom(from) }
    }
}
