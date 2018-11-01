package com.yveschiong.domain.usecases

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.entities.PersonDetailEntity
import io.reactivex.Single
import javax.inject.Inject

class GetPersonDetails @Inject constructor(private val repository: AppRepository) {
    fun get(personId: Int): Single<List<PersonDetailEntity>> {
        return repository.getPersonDetails(personId)
    }
}
