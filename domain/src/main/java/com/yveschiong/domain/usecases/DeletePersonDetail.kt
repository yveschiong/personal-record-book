package com.yveschiong.domain.usecases

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.entities.PersonDetailEntity
import io.reactivex.Single
import javax.inject.Inject

class DeletePersonDetail @Inject constructor(private val repository: AppRepository) {
    fun delete(personDetail: PersonDetailEntity): Single<Int> {
        return repository.deletePersonDetail(personDetail)
    }
}
