package com.yveschiong.personalrecordbook.ui.addpersondetail

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.AddPersonDetail
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.common.validators.PersonDetailValidator
import com.yveschiong.personalrecordbook.entities.PersonDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class AddPersonDetailViewModel(
    private val validator: PersonDetailValidator,
    private val useCase: AddPersonDetail,
    private val mapper: Mapper<PersonDetail, PersonDetailEntity>) :
    BaseViewModel() {

    var personDetail = PersonDetail(0, 0.0f, "", 0)

    var result: PublishSubject<Long> = PublishSubject.create()

    var detailDate: Long = 0
    var detailTime: Long = 0

    fun setDate(value: String) {
        detailDate = value.toLong()
    }

    fun setTime(value: String) {
        detailTime = value.toLong()
    }

    fun setDuration(value: String) {
        personDetail.duration = value.toFloat()
    }

    fun setSignature(value: String) {
        personDetail.signature = value
    }

    fun addButtonClicked() {
        personDetail.timestamp = detailDate + detailTime

        if (!validator.checkTimestamp(personDetail)) {
            return
        }

        if (!validator.checkDuration(personDetail)) {
            return
        }

        if (!validator.checkSignature(personDetail)) {
            return
        }

        if (!validator.checkPersonId(personDetail)) {
            return
        }

        useCase.add(mapper.mapFrom(personDetail))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.onNext(it)
            }, {
                result.onError(it)
            })
            .addToDisposables()
    }
}
