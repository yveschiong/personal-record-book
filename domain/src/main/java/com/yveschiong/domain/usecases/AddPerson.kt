package com.yveschiong.domain.usecases

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single
import javax.inject.Inject

class AddPerson @Inject constructor(private val repository: AppRepository) {
    fun add(person: PersonEntity): Single<Long> {
        return repository.addPerson(person)
    }
}
