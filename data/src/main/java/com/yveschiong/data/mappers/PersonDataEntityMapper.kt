package com.yveschiong.data.mappers

import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDataEntityMapper @Inject
constructor() : Mapper<PersonData, PersonEntity>() {
    override fun mapFrom(from: PersonData): PersonEntity {
        return PersonEntity(
            from.firstName, from.middleName, from.lastName, from.id
        )
    }

    override fun mapTo(from: PersonEntity): PersonData {
        return PersonData(
            from.firstName, from.middleName, from.lastName, from.id
        )
    }
}
