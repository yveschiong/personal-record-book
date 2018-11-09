package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.content.Context
import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.domain.AppRepository
import com.yveschiong.domain.usecases.AddPersonDetail
import com.yveschiong.personalrecordbook.common.validators.PersonDetailValidator
import com.yveschiong.personalrecordbook.mappers.PersonDetailPersonDetailEntityMapper
import dagger.Module
import dagger.Provides

@Module
class AddPersonDetailFragmentModule {
    @Provides
    fun provideInternalStorageManager(context: Context): InternalStorageManager {
        return InternalStorageManager(context)
    }

    @Provides
    fun provideAddPersonDetailUseCase(repository: AppRepository): AddPersonDetail {
        return AddPersonDetail(repository)
    }

    @Provides
    fun provideAddPersonDetailViewModelFactory(
        validator: PersonDetailValidator,
        useCase: AddPersonDetail,
        mapper: PersonDetailPersonDetailEntityMapper
    ): AddPersonDetailViewModelFactory {
        return AddPersonDetailViewModelFactory(validator, useCase, mapper)
    }
}
