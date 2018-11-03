package com.yveschiong.personalrecordbook.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yveschiong.personalrecordbook.common.extensions.defaultThreads
import com.yveschiong.personalrecordbook.common.extensions.toast
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var binding: T

    private val disposables = CompositeDisposable()

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    protected fun Disposable.addToDisposables() {
        disposables.add(this)
    }

    protected fun <V, T : Observable<V>> T.simpleSubscribe(func: (V) -> Unit) {
        simpleSubscribe(func, { context?.toast(it) })
    }

    protected fun <V, T : Observable<V>> T.simpleSubscribe(func: (V) -> Unit, error: (Throwable) -> Unit) {
        defaultThreads().subscribe(func, error).addToDisposables()
    }
}
