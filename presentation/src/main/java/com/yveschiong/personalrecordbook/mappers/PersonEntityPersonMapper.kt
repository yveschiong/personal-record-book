package com.yveschiong.personalrecordbook.mappers

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.personalrecordbook.entities.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonEntityPersonMapper @Inject
constructor() : Mapper<PersonEntity, Person>() {
    override fun mapFrom(from: PersonEntity): Person {
        return Person(
            from.firstName, from.middleName, from.lastName, from.id
        )
    }

    override fun mapTo(from: Person): PersonEntity {
        return PersonEntity(
            from.firstName, from.middleName, from.lastName, from.id
        )
    }
}
