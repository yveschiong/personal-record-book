package com.yveschiong.data.mappers

import com.yveschiong.data.entities.PersonDetailData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDetailEntityDataMapper @Inject
constructor() : Mapper<PersonDetailEntity, PersonDetailData>() {
    override fun mapFrom(from: PersonDetailEntity): PersonDetailData {
        return PersonDetailData(
            from.timestamp, from.duration, from.signature, from.personId, from.id
        )
    }
}
