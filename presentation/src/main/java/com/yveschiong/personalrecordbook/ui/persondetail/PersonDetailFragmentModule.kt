package com.yveschiong.personalrecordbook.ui.persondetail

import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.usecases.GetPersonDetails
import com.yveschiong.personalrecordbook.mappers.PersonDetailEntityPersonDetailMapper
import dagger.Module
import dagger.Provides

@Module
class PersonDetailFragmentModule {
    @Provides
    fun provideGetPersonDetailsUseCase(repository: AppRepository): GetPersonDetails {
        return GetPersonDetails(repository)
    }

    @Provides
    fun providePersonDetailViewModelFactory(
        useCase: GetPersonDetails,
        mapper: PersonDetailEntityPersonDetailMapper
    ): PersonDetailViewModelFactory {
        return PersonDetailViewModelFactory(useCase, mapper)
    }
}
