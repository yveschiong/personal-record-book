package com.yveschiong.personalrecordbook.common.rules

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FloatRule @Inject constructor() : Rule<Float> {
    override fun validate(t: Float): Boolean {
        return t > 0
    }
}