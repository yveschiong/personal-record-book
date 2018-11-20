package com.yveschiong.personalrecordbook.mappers

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.personalrecordbook.entities.PersonDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDetailPersonDetailEntityMapper @Inject
constructor() : Mapper<PersonDetail, PersonDetailEntity>() {
    override fun mapFrom(from: PersonDetail): PersonDetailEntity {
        return PersonDetailEntity(
            from.startTimestamp, from.endTimestamp, from.signatureFilePath, from.personId, from.id
        )
    }
}
