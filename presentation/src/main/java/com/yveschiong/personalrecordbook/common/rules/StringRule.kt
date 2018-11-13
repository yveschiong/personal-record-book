package com.yveschiong.personalrecordbook.common.rules

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringRule @Inject constructor() : Rule<String> {
    override fun validate(t: String): Boolean {
        return !t.isEmpty()
    }
}