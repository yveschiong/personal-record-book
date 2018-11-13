package com.yveschiong.personalrecordbook.ui.addpersondetail

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.usecases.AddPersonDetail
import com.yveschiong.personalrecordbook.common.rules.RegularPersonDetailRule
import com.yveschiong.personalrecordbook.mappers.PersonDetailPersonDetailEntityMapper
import dagger.Module
import dagger.Provides

@Module
class AddPersonDetailFragmentModule {
    @Provides
    fun provideAddPersonDetailUseCase(repository: AppRepository): AddPersonDetail {
        return AddPersonDetail(repository)
    }

    @Provides
    fun provideAddPersonDetailViewModelFactory(
        rule: RegularPersonDetailRule,
        useCase: AddPersonDetail,
        mapper: PersonDetailPersonDetailEntityMapper
    ): AddPersonDetailViewModelFactory {
        return AddPersonDetailViewModelFactory(rule, useCase, mapper)
    }
}
