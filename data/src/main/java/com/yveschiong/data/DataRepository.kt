package com.yveschiong.data

import com.yveschiong.data.entities.PersonData
import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource,
    private val personMapper: Mapper<PersonData, PersonEntity>
) : AppRepository {
    override fun getPeople(): Single<List<PersonEntity>> {
        // For now we will be fetching from the local data source
        // since we don't have a remote one
        return localDataSource.getPeople().map { personMapper.mapFrom(it) }
    }

    override fun addPerson(person: PersonEntity): Single<Long> {
        return localDataSource.addPerson(person)
    }
}
