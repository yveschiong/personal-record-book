package com.yveschiong.domain.usecases

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class GetPeople @Inject constructor(private val repository: AppRepository) {
    fun get(): Single<List<PersonEntity>> {
        return repository.getPeople()
    }
}
