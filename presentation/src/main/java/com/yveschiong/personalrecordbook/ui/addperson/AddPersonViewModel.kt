package com.yveschiong.personalrecordbook.ui.addperson

import android.arch.lifecycle.MutableLiveData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.domain.usecases.AddPerson
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.common.rules.PersonRule
import com.yveschiong.personalrecordbook.entities.Person
import io.reactivex.subjects.PublishSubject

class AddPersonViewModel(
    private val rule: PersonRule,
    private val useCase: AddPerson,
    private val mapper: Mapper<Person, PersonEntity>) :
    BaseViewModel() {

    private val person = Person()

    var firstName = MutableLiveData<String>()
    var middleName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var license = MutableLiveData<String>()

    var result: PublishSubject<Long> = PublishSubject.create()

    var showFirstNameError = MutableLiveData<Boolean>()

    fun addButtonClicked() {
        person.firstName = firstName.value ?: ""
        person.middleName = middleName.value ?: ""
        person.lastName = lastName.value ?: ""
        person.license = license.value ?: ""

        showFirstNameError.value = !rule.validateFirstName(person)

        if (!rule.validate(person)) {
            return
        }

        useCase.add(mapper.mapFrom(person)).simpleSubscribe(result)
    }
}
