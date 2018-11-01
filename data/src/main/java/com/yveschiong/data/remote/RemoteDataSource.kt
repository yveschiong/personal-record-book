package com.yveschiong.data.remote

import com.yveschiong.data.DataSource
import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor() : DataSource {
    override fun getPeople(): Single<List<PersonData>> {
        TODO("not implemented")
    }

    override fun addPerson(person: PersonEntity): Single<Long> {
        TODO("not implemented")
    }
}