package com.yveschiong.data

import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface DataSource {
    fun getPeople(): Single<List<PersonData>>
    fun addPerson(person: PersonEntity): Single<Long>
}
