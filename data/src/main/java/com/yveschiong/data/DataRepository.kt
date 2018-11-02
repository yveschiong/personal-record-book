package com.yveschiong.data

import com.yveschiong.data.entities.PersonData
import com.yveschiong.data.entities.PersonDetailData
import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource,
    private val personMapper: Mapper<PersonData, PersonEntity>,
    private val personDetailMapper: Mapper<PersonDetailData, PersonDetailEntity>
) : AppRepository {
    // For now we will be operating on the local data source
    // since we don't have a remote one currently
    override fun getPeople(): Single<List<PersonEntity>> {
        return localDataSource.getPeople().map { personMapper.mapFrom(it) }
    }

    override fun addPerson(person: PersonEntity): Single<Long> {
        return localDataSource.addPerson(person)
    }

    override fun getPersonDetails(personId: Int): Single<List<PersonDetailEntity>> {
        return localDataSource.getPersonDetails(personId).map { personDetailMapper.mapFrom(it) }
    }

    override fun addPersonDetail(personDetail: PersonDetailEntity): Single<Long> {
        return localDataSource.addPersonDetail(personDetail)
    }
}
