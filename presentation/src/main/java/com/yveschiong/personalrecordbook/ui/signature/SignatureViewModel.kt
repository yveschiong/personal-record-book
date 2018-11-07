package com.yveschiong.personalrecordbook.ui.signature

import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import io.reactivex.subjects.PublishSubject

class SignatureViewModel :
    BaseViewModel() {

    var clickedSaveSignature: PublishSubject<Unit> = PublishSubject.create()

    fun saveSignature() {
        clickedSaveSignature.onNext(Unit)
    }
}
