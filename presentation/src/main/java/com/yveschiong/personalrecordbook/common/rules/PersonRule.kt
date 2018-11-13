package com.yveschiong.personalrecordbook.common.rules

import com.yveschiong.personalrecordbook.entities.Person

abstract class PersonRule : Rule<Person> {

    abstract fun validateFirstName(person: Person): Boolean
    abstract fun validateMiddleName(person: Person): Boolean
    abstract fun validateLastName(person: Person): Boolean
    abstract fun validateLicense(person: Person): Boolean

    override fun validate(t: Person): Boolean {
        return validateFirstName(t) && validateMiddleName(t)
            && validateLastName(t) && validateLicense(t)
    }
}