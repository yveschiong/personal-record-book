package com.yveschiong.personalrecordbook.common.rules

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimestampRule @Inject constructor() : Rule<Long> {
    override fun validate(t: Long): Boolean {
        return t >= 0
    }
}