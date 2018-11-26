package com.yveschiong.personalrecordbook.ui.editpersondetail

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.DeletePersonDetail
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.entities.PersonDetail
import io.reactivex.subjects.PublishSubject

class EditPersonDetailViewModel(
    private val useCase: DeletePersonDetail,
    private val mapper: Mapper<PersonDetail, PersonDetailEntity>) :
    BaseViewModel() {

    var personDetail: PersonDetail? = null

    var result: PublishSubject<Long> = PublishSubject.create()

    fun deleteButtonClicked() {
        personDetail?.let {
            useCase.delete(mapper.mapFrom(it)).simpleSubscribe { result }
        }
    }
}
