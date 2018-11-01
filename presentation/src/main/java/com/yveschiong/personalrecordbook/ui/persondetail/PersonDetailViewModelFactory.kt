package com.yveschiong.personalrecordbook.ui.persondetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.GetPersonDetails
import com.yveschiong.personalrecordbook.entities.PersonDetail

class PersonDetailViewModelFactory(
    private val useCase: GetPersonDetails,
    private val mapper: Mapper<PersonDetailEntity, PersonDetail>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonDetailViewModel::class.java)) {
            return PersonDetailViewModel(useCase, mapper) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
