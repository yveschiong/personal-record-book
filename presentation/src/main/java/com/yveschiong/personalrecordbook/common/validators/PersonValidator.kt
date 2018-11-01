package com.yveschiong.personalrecordbook.common.validators

import com.yveschiong.personalrecordbook.entities.Person
import javax.inject.Inject

class PersonValidator @Inject constructor() {
    fun checkFirstName(data: Person): Boolean {
        if (data.firstName.isEmpty()) {
            return false
        }

        return true
    }

    fun checkLastName(data: Person): Boolean {
        if (data.lastName.isEmpty()) {
            return false
        }

        return true
    }
}