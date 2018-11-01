package com.yveschiong.personalrecordbook.mappers

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.personalrecordbook.entities.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonEntityMapper @Inject
constructor() : Mapper<Person, PersonEntity>() {
    override fun mapFrom(from: Person): PersonEntity {
        return PersonEntity(
            from.firstName, from.middleName, from.lastName, from.id
        )
    }
}
