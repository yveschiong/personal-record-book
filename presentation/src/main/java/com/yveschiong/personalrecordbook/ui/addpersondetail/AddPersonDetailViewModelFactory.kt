package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.AddPersonDetail
import com.yveschiong.personalrecordbook.common.validators.PersonDetailValidator
import com.yveschiong.personalrecordbook.entities.PersonDetail

class AddPersonDetailViewModelFactory(
    private val validator: PersonDetailValidator,
    private val useCase: AddPersonDetail,
    private val mapper: Mapper<PersonDetail, PersonDetailEntity>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPersonDetailViewModel::class.java)) {
            return AddPersonDetailViewModel(validator, useCase, mapper) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
