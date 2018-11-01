package com.yveschiong.personalrecordbook.ui.people

import android.arch.lifecycle.MutableLiveData
import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.domain.usecases.GetPeople
import com.yveschiong.personalrecordbook.common.base.BaseViewModel
import com.yveschiong.personalrecordbook.entities.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PeopleViewModel(
    private val useCase: GetPeople,
    private val mapper: Mapper<PersonEntity, Person>) :
    BaseViewModel() {

    val people: MutableLiveData<List<Person>> = MutableLiveData()
    var result: PublishSubject<List<Person>> = PublishSubject.create()

    fun fetch() {
        useCase.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.onNext(mapper.mapFrom(it))
            }, {
                result.onError(it)
            })
            .addToDisposables()
    }
}
