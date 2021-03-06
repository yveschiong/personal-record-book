package com.yveschiong.personalrecordbook.ui.addperson

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.domain.usecases.AddPerson
import com.yveschiong.personalrecordbook.common.rules.PersonRule
import com.yveschiong.personalrecordbook.entities.Person

class AddPersonViewModelFactory(
    private val rule: PersonRule,
    private val useCase: AddPerson,
    private val mapper: Mapper<Person, PersonEntity>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPersonViewModel::class.java)) {
            return AddPersonViewModel(rule, useCase, mapper) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
