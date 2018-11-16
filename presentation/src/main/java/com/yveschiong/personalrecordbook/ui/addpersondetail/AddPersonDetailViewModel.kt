package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.arch.lifecycle.MutableLiveData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.AddPersonDetail
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.common.extensions.default
import com.yveschiong.personalrecordbook.common.extensions.getDate
import com.yveschiong.personalrecordbook.common.extensions.getTime
import com.yveschiong.personalrecordbook.common.rules.PersonDetailRule
import com.yveschiong.personalrecordbook.entities.PersonDetail
import io.reactivex.subjects.PublishSubject
import java.util.*

class AddPersonDetailViewModel(
    private val rule: PersonDetailRule,
    private val useCase: AddPersonDetail,
    private val mapper: Mapper<PersonDetail, PersonDetailEntity>) :
    BaseViewModel() {

    private var personDetail = PersonDetail()

    var clickedDate: PublishSubject<Unit> = PublishSubject.create()
    var clickedTime: PublishSubject<Unit> = PublishSubject.create()
    var clickedSignature: PublishSubject<Unit> = PublishSubject.create()
    var clickedAddRecord: PublishSubject<Unit> = PublishSubject.create()

    var result: PublishSubject<Long> = PublishSubject.create()

    var showDurationError = MutableLiveData<Boolean>()
    var showSignatureError = MutableLiveData<Boolean>()

    private val currentTimestamp = Calendar.getInstance().timeInMillis

    val date = MutableLiveData<String>().default(currentTimestamp.getDate())
    val time = MutableLiveData<String>().default(currentTimestamp.getTime())
    val signaturePath = MutableLiveData<String>()

    var dateTimestamp = currentTimestamp
        set(value) {
            field = value
            date.value = value.getDate()
        }

    var timeTimestamp = currentTimestamp
        set(value) {
            field = value
            time.value = value.getTime()
        }

    var personId: Int = 0
        set(value) {
            field = value
            personDetail.personId = value
        }

    fun setDuration(value: String) {
        try {
            personDetail.duration = value.toFloat()
        } catch (exception: NumberFormatException) {
            // Pass through
        }
    }

    fun setTimestamp(dateTimestamp: Long, timeTimestamp: Long) {
        val time = Calendar.getInstance()
        time.timeInMillis = timeTimestamp

        val date = Calendar.getInstance()
        date.timeInMillis = dateTimestamp
        date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY))
        date.set(Calendar.MINUTE, time.get(Calendar.MINUTE))

        personDetail.timestamp = date.timeInMillis
    }

    fun setSignatureFilePath(path: String) {
        personDetail.signatureFilePath = path
    }

    fun dateButtonClicked() {
        clickedDate.onNext(Unit)
    }

    fun timeButtonClicked() {
        clickedTime.onNext(Unit)
    }

    fun signatureButtonClicked() {
        clickedSignature.onNext(Unit)
    }

    fun isPathNullOrEmpty(value: String?): Boolean {
        return value.isNullOrEmpty()
    }

    fun updateSignatureError() {
        setSignatureFilePath(signaturePath.value ?: personDetail.signatureFilePath)

        showSignatureError.value = !rule.validateSignatureFilePath(personDetail)
    }

    fun addButtonClicked() {
        setTimestamp(dateTimestamp, timeTimestamp)

        updateSignatureError()

        showDurationError.value = !rule.validateDuration(personDetail)

        if (!rule.validate(personDetail)) {
            return
        }

        clickedAddRecord.onNext(Unit)
    }

    fun addPersonDetail() {
        useCase.add(mapper.mapFrom(personDetail)).simpleSubscribe(result)
    }
}
