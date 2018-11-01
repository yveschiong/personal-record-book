package com.yveschiong.personalrecordbook.common.listeners

interface OnAdapterViewClicked<T> {
    fun onClicked(data: T)
}
