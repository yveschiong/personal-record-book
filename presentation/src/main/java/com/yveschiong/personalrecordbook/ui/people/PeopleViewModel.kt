package com.yveschiong.personalrecordbook.ui.people

import com.yveschiong.domain.common.Mapper
import com.yveschiong.domain.entities.PersonEntity
import com.yveschiong.domain.usecases.GetPeople
import com.yveschiong.personalrecordbook.common.BaseViewModel
import com.yveschiong.personalrecordbook.entities.Person

class PeopleViewModel(private val getPeople: GetPeople, private val mapper: Mapper<PersonEntity, Person>) :
    BaseViewModel()
