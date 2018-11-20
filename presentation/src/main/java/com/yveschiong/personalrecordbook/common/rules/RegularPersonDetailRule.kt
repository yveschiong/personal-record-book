package com.yveschiong.personalrecordbook.common.rules

import com.yveschiong.personalrecordbook.entities.PersonDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegularPersonDetailRule @Inject constructor(
    private val timestampRule: TimestampRule,
    private val complexTimestampRule: ComplexTimestampRule,
    private val stringRule: StringRule,
    private val intRule: IntRule
) : PersonDetailRule() {
    override fun validateTimestamp(personDetail: PersonDetail): Boolean {
        return timestampRule.validate(personDetail.startTimestamp)
            && timestampRule.validate(personDetail.endTimestamp)
            && complexTimestampRule.validate(personDetail.startTimestamp, personDetail.endTimestamp)
    }

    override fun validateSignatureFilePath(personDetail: PersonDetail): Boolean {
        return stringRule.validate(personDetail.signatureFilePath)
    }

    override fun validatePersonId(personDetail: PersonDetail): Boolean {
        return intRule.validate(personDetail.personId)
    }
}