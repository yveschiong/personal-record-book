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

    var clickedStartDate: PublishSubject<Unit> = PublishSubject.create()
    var clickedStartTime: PublishSubject<Unit> = PublishSubject.create()
    var clickedEndDate: PublishSubject<Unit> = PublishSubject.create()
    var clickedEndTime: PublishSubject<Unit> = PublishSubject.create()
    var clickedSignature: PublishSubject<Unit> = PublishSubject.create()
    var clickedAddRecord: PublishSubject<Unit> = PublishSubject.create()

    var result: PublishSubject<Long> = PublishSubject.create()

    var showSignatureError = MutableLiveData<Boolean>()

    private val currentTimestamp = Calendar.getInstance().timeInMillis

    val startDate = MutableLiveData<String>().default(currentTimestamp.getDate())
    val startTime = MutableLiveData<String>().default(currentTimestamp.getTime())
    val endDate = MutableLiveData<String>().default(currentTimestamp.getDate())
    val endTime = MutableLiveData<String>().default(currentTimestamp.getTime())
    val signaturePath = MutableLiveData<String>()

    var startDateTimestamp = currentTimestamp
        set(value) {
            field = value
            startDate.value = value.getDate()
        }

    var startTimeTimestamp = currentTimestamp
        set(value) {
            field = value
            startTime.value = value.getTime()
        }

    var endDateTimestamp = currentTimestamp
        set(value) {
            field = value
            endDate.value = value.getDate()
        }

    var endTimeTimestamp = currentTimestamp
        set(value) {
            field = value
            endTime.value = value.getTime()
        }

    var personId: Int = 0
        set(value) {
            field = value
            personDetail.personId = value
        }

    fun setStartTimestamp(dateTimestamp: Long, timeTimestamp: Long) {
        personDetail.startTimestamp = generateTimestamp(dateTimestamp, timeTimestamp).timeInMillis
    }

    fun setEndTimestamp(dateTimestamp: Long, timeTimestamp: Long) {
        personDetail.endTimestamp = generateTimestamp(dateTimestamp, timeTimestamp).timeInMillis
    }

    private fun generateTimestamp(dateTimestamp: Long, timeTimestamp: Long): Calendar {
        val time = Calendar.getInstance()
        time.timeInMillis = timeTimestamp

        val date = Calendar.getInstance()
        date.timeInMillis = dateTimestamp
        date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY))
        date.set(Calendar.MINUTE, time.get(Calendar.MINUTE))

        return date
    }

    fun setSignatureFilePath(path: String) {
        personDetail.signatureFilePath = path
    }

    fun startDateButtonClicked() {
        clickedStartDate.onNext(Unit)
    }

    fun startTimeButtonClicked() {
        clickedStartTime.onNext(Unit)
    }

    fun endDateButtonClicked() {
        clickedEndDate.onNext(Unit)
    }

    fun endTimeButtonClicked() {
        clickedEndTime.onNext(Unit)
    }

    fun signatureButtonClicked() {
        clickedSignature.onNext(Unit)
    }

    fun isPathNullOrEmpty(value: String?): Boolean {
        return value.isNullOrEmpty()
    }

    fun updateSignatureError() {
        // For now we will set it to the absolute file path for the cache file just for validation
        setSignatureFilePath(signaturePath.value ?: personDetail.signatureFilePath)

        showSignatureError.value = !rule.validateSignatureFilePath(personDetail)
    }

    fun addButtonClicked() {
        setStartTimestamp(startDateTimestamp, startTimeTimestamp)
        setEndTimestamp(endDateTimestamp, endTimeTimestamp)

        // This will still check the cache file and if it validates then we can set the path
        // to be the relative file path for the internal file in the fragment
        showSignatureError.value = !rule.validateSignatureFilePath(personDetail)

        if (!rule.validate(personDetail)) {
            return
        }

        clickedAddRecord.onNext(Unit)
    }

    fun addPersonDetail() {
        useCase.add(mapper.mapFrom(personDetail)).simpleSubscribe(result)
    }
}
