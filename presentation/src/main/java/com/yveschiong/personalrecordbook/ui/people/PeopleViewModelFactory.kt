package com.yveschiong.personalrecordbook.ui.people

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.domain.usecases.GetPeople
import com.yveschiong.personalrecordbook.entities.Person

class PeopleViewModelFactory(private val getPeople: GetPeople, private val mapper: Mapper<PersonEntity, Person>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeopleViewModel::class.java)) {
            return PeopleViewModel(getPeople, mapper) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
