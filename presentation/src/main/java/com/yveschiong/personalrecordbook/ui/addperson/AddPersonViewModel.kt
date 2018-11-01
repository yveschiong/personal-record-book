package com.yveschiong.personalrecordbook.ui.addperson

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.domain.usecases.AddPerson
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.common.validators.PersonValidator
import com.yveschiong.personalrecordbook.entities.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class AddPersonViewModel(
    private val validator: PersonValidator,
    private val useCase: AddPerson,
    private val mapper: Mapper<Person, PersonEntity>) :
    BaseViewModel() {

    var person: Person = Person("", "", "", "")

    var result: PublishSubject<Long> = PublishSubject.create()

    fun setFirstName(value: String) {
        person.firstName = value
    }

    fun setMiddleName(value: String) {
        person.middleName = value
    }

    fun setLastName(value: String) {
        person.lastName = value
    }

    fun setLicense(value: String) {
        person.license = value
    }

    fun clicked() {
        if (!validator.checkFirstName(person)) {
            return
        }

        if (!validator.checkLastName(person)) {
            return
        }

        if (!validator.checkLicense(person)) {
            return
        }

        useCase.add(mapper.mapFrom(person))
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
