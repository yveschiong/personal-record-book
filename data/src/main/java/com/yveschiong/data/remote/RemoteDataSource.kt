package com.yveschiong.data.remote

import com.yveschiong.data.DataSource
import com.yveschiong.data.entities.PersonData
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor() : DataSource {
    override fun getPeople(): Single<List<PersonData>> {
        TODO("not implemented")
    }
}