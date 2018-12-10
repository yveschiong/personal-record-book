package com.yveschiong.personalrecordbook.ui.editpersondetail

import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.DeletePersonDetail
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.entities.PersonDetail
import io.reactivex.subjects.PublishSubject

class EditPersonDetailViewModel(
    private val useCase: DeletePersonDetail,
    private val mapper: Mapper<PersonDetail, PersonDetailEntity>,
    val manager: InternalStorageManager
) : BaseViewModel() {

    var detail: PersonDetail? = null

    var result: PublishSubject<Long> = PublishSubject.create()

    fun deleteButtonClicked() {
        detail?.let {
            useCase.delete(mapper.mapFrom(it)).simpleSubscribe { result }
        }
    }
}
