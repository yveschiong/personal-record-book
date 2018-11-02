package com.yveschiong.domain.usecases

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.entities.PersonDetailEntity
import io.reactivex.Single
import javax.inject.Inject

class AddPersonDetail @Inject constructor(private val repository: AppRepository) {
    fun add(personDetail: PersonDetailEntity): Single<Long> {
        return repository.addPersonDetail(personDetail)
    }
}
