package com.yveschiong.personalrecordbook.di

import com.yveschiong.data.DataRepository
import com.yveschiong.data.local.LocalDataSource
import com.yveschiong.data.local.PeopleDao
import com.yveschiong.data.mappers.PersonDataEntityMapper
import com.yveschiong.data.mappers.PersonEntityDataMapper
import com.yveschiong.data.remote.RemoteDataSource
import com.yveschiong.domain.AppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(
        dao: PeopleDao,
        mapper: PersonEntityDataMapper
    ): LocalDataSource {
        return LocalDataSource(dao, mapper)
    }

    @Singleton
    @Provides
    fun provideAppRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        mapper: PersonDataEntityMapper
    ): AppRepository {
        return DataRepository(localDataSource, remoteDataSource, mapper)
    }
}