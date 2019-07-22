package com.yveschiong.personalrecordbook.ui.people

import com.yveschiong.domain.usecases.GetPeople
import com.yveschiong.personalrecordbook.mappers.PersonEntityPersonMapper
import dagger.Module
import dagger.Provides

@Module
class PeopleFragmentModule {
    @Provides
    fun providePeopleViewModelFactory(
        useCase: GetPeople,
        mapper: PersonEntityPersonMapper
    ): PeopleViewModelFactory {
        return PeopleViewModelFactory(useCase, mapper)
    }
}
