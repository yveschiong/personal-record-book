package com.yveschiong.data.local

import com.yveschiong.data.DataSource
import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: PeopleDao,
    private val mapper: Mapper<PersonEntity, PersonData>
) : DataSource {
    override fun getPeople(): Single<List<PersonData>> {
        return dao.getPeople().map { mapper.mapFrom(it) }
    }

    override fun addPerson(person: PersonEntity): Single<Long> {
        return Single.fromCallable { dao.insert(person) }
    }
}