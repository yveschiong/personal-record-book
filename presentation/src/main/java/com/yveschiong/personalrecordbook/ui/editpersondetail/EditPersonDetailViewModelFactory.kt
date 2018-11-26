package com.yveschiong.personalrecordbook.ui.editpersondetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.DeletePersonDetail
import com.yveschiong.personalrecordbook.entities.PersonDetail

class EditPersonDetailViewModelFactory(
    private val useCase: DeletePersonDetail,
    private val mapper: Mapper<PersonDetail, PersonDetailEntity>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditPersonDetailViewModel::class.java)) {
            return EditPersonDetailViewModel(useCase, mapper) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
