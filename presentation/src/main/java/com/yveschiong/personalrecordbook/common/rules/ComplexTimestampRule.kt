package com.yveschiong.personalrecordbook.common.rules

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComplexTimestampRule @Inject constructor() : ComplexRule<Long, Long> {
    override fun validate(t: Long, v: Long): Boolean {
        return v >= t
    }
}