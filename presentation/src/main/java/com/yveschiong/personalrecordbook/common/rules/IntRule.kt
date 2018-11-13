package com.yveschiong.personalrecordbook.common.rules

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntRule @Inject constructor() : Rule<Int> {
    override fun validate(t: Int): Boolean {
        return t >= 0
    }
}