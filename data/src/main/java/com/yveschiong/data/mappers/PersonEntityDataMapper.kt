package com.yveschiong.data.mappers

import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonEntityDataMapper @Inject
constructor() : Mapper<PersonEntity, PersonData>() {
    override fun mapFrom(from: PersonEntity): PersonData {
        return PersonData(
            from.firstName, from.middleName, from.lastName, from.id
        )
    }
}
