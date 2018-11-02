package com.yveschiong.personalrecordbook.common.validators

import com.yveschiong.personalrecordbook.entities.PersonDetail
import javax.inject.Inject

class PersonDetailValidator @Inject constructor() {
    fun checkTimestamp(data: PersonDetail): Boolean {
        if (data.timestamp <= 0L) {
            return false
        }

        return true
    }

    fun checkDuration(data: PersonDetail): Boolean {
        if (data.duration <= 0.0f) {
            return false
        }

        return true
    }

    fun checkSignature(data: PersonDetail): Boolean {
        if (data.signature.isEmpty()) {
            return false
        }

        return true
    }

    fun checkPersonId(data: PersonDetail): Boolean {
        if (data.personId <= 0) {
            return false
        }

        return true
    }
}