package com.yveschiong.personalrecordbook.common.rules

interface Rule<T> {
    fun validate(t: T): Boolean
}