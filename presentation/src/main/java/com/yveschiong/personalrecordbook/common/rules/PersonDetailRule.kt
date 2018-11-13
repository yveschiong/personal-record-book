package com.yveschiong.personalrecordbook.common.rules

import com.yveschiong.personalrecordbook.entities.PersonDetail

abstract class PersonDetailRule : Rule<PersonDetail> {

    abstract fun validateTimestamp(personDetail: PersonDetail): Boolean
    abstract fun validateDuration(personDetail: PersonDetail): Boolean
    abstract fun validateSignatureFilePath(personDetail: PersonDetail): Boolean
    abstract fun validatePersonId(personDetail: PersonDetail): Boolean

    override fun validate(t: PersonDetail): Boolean {
        return validateTimestamp(t) && validateDuration(t)
            && validateSignatureFilePath(t) && validatePersonId(t)
    }
}