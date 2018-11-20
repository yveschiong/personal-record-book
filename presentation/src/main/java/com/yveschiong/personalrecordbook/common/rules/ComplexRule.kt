package com.yveschiong.personalrecordbook.common.rules

interface ComplexRule<T, V> {
    fun validate(t: T, v: V): Boolean
}