package com.yveschiong.domain

import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface AppRepository {
    fun getPeople(): Single<List<PersonEntity>>
    fun addPerson(person: PersonEntity): Single<Long>
    fun getPersonDetails(personId: Int): Single<List<PersonDetailEntity>>
    fun addPersonDetail(personDetail: PersonDetailEntity): Single<Long>
}
