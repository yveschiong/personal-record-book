package com.yveschiong.data.local

import com.yveschiong.data.DataSource
import com.yveschiong.data.entities.PersonData
import com.yveschiong.data.entities.PersonDetailData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val peopleDao: PeopleDao,
    private val peopleDetailsDao: PeopleDetailsDao,
    private val personMapper: Mapper<PersonEntity, PersonData>,
    private val personDetailMapper: Mapper<PersonDetailEntity, PersonDetailData>
) : DataSource {
    override fun getPeople(): Single<List<PersonData>> {
        return peopleDao.getPeople().map { personMapper.mapFrom(it) }
    }

    override fun addPerson(person: PersonEntity): Single<Long> {
        return Single.fromCallable { peopleDao.insert(person) }
    }

    override fun getPersonDetails(personId: Int): Single<List<PersonDetailData>> {
        return peopleDetailsDao.getPersonDetails(personId).map { personDetailMapper.mapFrom(it) }
    }

    override fun addPersonDetail(personDetail: PersonDetailEntity): Single<Long> {
        return Single.fromCallable { peopleDetailsDao.insert(personDetail) }
    }

    override fun deletePersonDetail(personDetail: PersonDetailEntity): Single<Int> {
        return Single.fromCallable { peopleDetailsDao.delete(personDetail) }
    }
}