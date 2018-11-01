package com.yveschiong.data.mappers

import com.yveschiong.data.entities.PersonDetailData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDetailDataEntityMapper @Inject
constructor() : Mapper<PersonDetailData, PersonDetailEntity>() {
    override fun mapFrom(from: PersonDetailData): PersonDetailEntity {
        return PersonDetailEntity(
            from.timestamp, from.duration, from.signature, from.personId, from.id
        )
    }
}
