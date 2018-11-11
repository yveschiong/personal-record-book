package com.yveschiong.personalrecordbook.ui.signature

import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import io.reactivex.subjects.PublishSubject

class SignatureViewModel :
    BaseViewModel() {

    var signedSignature: PublishSubject<Unit> = PublishSubject.create()
    var clickedClearSignature: PublishSubject<Unit> = PublishSubject.create()
    var clickedSaveSignature: PublishSubject<Unit> = PublishSubject.create()

    fun signedSignature() {
        signedSignature.onNext(Unit)
    }

    fun clearSignature() {
        clickedClearSignature.onNext(Unit)
    }

    fun saveSignature() {
        clickedSaveSignature.onNext(Unit)
    }
}
