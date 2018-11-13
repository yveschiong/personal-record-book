package com.yveschiong.personalrecordbook.common.rules

import com.yveschiong.personalrecordbook.entities.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegularPersonRule @Inject constructor(private val stringRule: StringRule) : PersonRule() {
    override fun validateFirstName(person: Person): Boolean {
        return stringRule.validate(person.firstName)
    }

    override fun validateMiddleName(person: Person): Boolean {
        // Allow no middle name
        return true
    }

    override fun validateLastName(person: Person): Boolean {
        return stringRule.validate(person.lastName)
    }

    override fun validateLicense(person: Person): Boolean {
        return stringRule.validate(person.license)
    }
}