package com.yveschiong.data.repositories

import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(private val personMapper: Mapper<PersonEntity, PersonData>) : AppRepository {
    override fun getPeople(): Single<List<PersonEntity>> {
        TODO("not implemented")
    }
}
