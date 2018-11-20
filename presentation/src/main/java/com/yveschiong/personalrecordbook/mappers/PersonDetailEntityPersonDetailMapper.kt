package com.yveschiong.personalrecordbook.mappers

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.personalrecordbook.entities.PersonDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDetailEntityPersonDetailMapper @Inject
constructor() : Mapper<PersonDetailEntity, PersonDetail>() {
    override fun mapFrom(from: PersonDetailEntity): PersonDetail {
        return PersonDetail(
            from.startTimestamp, from.endTimestamp, from.signatureFilePath, from.personId, from.id
        )
    }
}
