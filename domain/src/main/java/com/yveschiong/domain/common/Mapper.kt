package com.yveschiong.domain.common

import io.reactivex.Observable

import java.util.ArrayList

abstract class Mapper<E, T> {
    abstract fun mapFrom(from: E): T
    abstract fun mapTo(from: T): E

    fun mapFrom(from: List<E>): List<T> {
        val list = ArrayList<T>()
        for (item in from) {
            list.add(mapFrom(item))
        }

        return list
    }

    fun mapTo(from: List<T>): List<E> {
        val list = ArrayList<E>()
        for (item in from) {
            list.add(mapTo(item))
        }

        return list
    }

    fun mapFromObservable(from: E): Observable<T> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun mapFromObservable(from: List<E>): Observable<List<T>> {
        return Observable.fromCallable { mapFrom(from) }
    }

    fun mapToObservable(from: T): Observable<E> {
        return Observable.fromCallable { mapTo(from) }
    }

    fun mapToObservable(from: List<T>): Observable<List<E>> {
        return Observable.fromCallable { mapTo(from) }
    }
}
