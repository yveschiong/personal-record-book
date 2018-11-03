package com.yveschiong.personalrecordbook.ui.persondetail

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.usecases.GetPersonDetails
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.entities.Person
import com.yveschiong.personalrecordbook.entities.PersonDetail
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PersonDetailViewModel(
    private val useCase: GetPersonDetails,
    private val mapper: Mapper<PersonDetailEntity, PersonDetail>) :
    BaseViewModel() {

    var result: PublishSubject<List<PersonDetail>> = PublishSubject.create()
    var clicked: PublishSubject<Person> = PublishSubject.create()

    fun fetch(personId: Int) {
        useCase.get(personId)
            .observeOn(Schedulers.io())
            .flatMap {
                Single.just(mapper.mapFrom(it))
            }
            .simpleSubscribe(result)
    }

    fun onFabClicked(person: Person) {
        clicked.onNext(person)
    }
}
