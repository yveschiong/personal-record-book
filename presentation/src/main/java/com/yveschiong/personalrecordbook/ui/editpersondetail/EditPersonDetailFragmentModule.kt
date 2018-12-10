package com.yveschiong.personalrecordbook.ui.editpersondetail

import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.usecases.DeletePersonDetail
import com.yveschiong.personalrecordbook.mappers.PersonDetailPersonDetailEntityMapper
import dagger.Module
import dagger.Provides

@Module
class EditPersonDetailFragmentModule {
    @Provides
    fun provideDeletePersonDetailUseCase(repository: AppRepository): DeletePersonDetail {
        return DeletePersonDetail(repository)
    }

    @Provides
    fun provideEditPersonDetailViewModelFactory(
        useCase: DeletePersonDetail,
        mapper: PersonDetailPersonDetailEntityMapper,
        internalStorageManager: InternalStorageManager
    ): EditPersonDetailViewModelFactory {
        return EditPersonDetailViewModelFactory(useCase, mapper, internalStorageManager)
    }
}
