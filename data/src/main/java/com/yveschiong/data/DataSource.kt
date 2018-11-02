package com.yveschiong.data

import com.yveschiong.data.entities.PersonData
import com.yveschiong.data.entities.PersonDetailData
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface DataSource {
    fun getPeople(): Single<List<PersonData>>
    fun addPerson(person: PersonEntity): Single<Long>
    fun getPersonDetails(personId: Int): Single<List<PersonDetailData>>
    fun addPersonDetail(personDetail: PersonDetailEntity): Single<Long>
}
