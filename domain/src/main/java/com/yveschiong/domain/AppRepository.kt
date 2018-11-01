package com.yveschiong.domain

import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface AppRepository {
    fun getPeople(): Single<List<PersonEntity>>
    fun addPerson(person: PersonEntity): Single<Long>
}
