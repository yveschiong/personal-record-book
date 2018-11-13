package com.yveschiong.personalrecordbook.ui.addperson

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.usecases.AddPerson
import com.yveschiong.personalrecordbook.common.rules.RegularPersonRule
import com.yveschiong.personalrecordbook.mappers.PersonEntityMapper
import dagger.Module
import dagger.Provides

@Module
class AddPersonFragmentModule {
    @Provides
    fun provideAddPersonUseCase(repository: AppRepository): AddPerson {
        return AddPerson(repository)
    }

    @Provides
    fun provideAddPersonViewModelFactory(
        rule: RegularPersonRule,
        useCase: AddPerson,
        mapper: PersonEntityMapper
    ): AddPersonViewModelFactory {
        return AddPersonViewModelFactory(rule, useCase, mapper)
    }
}
