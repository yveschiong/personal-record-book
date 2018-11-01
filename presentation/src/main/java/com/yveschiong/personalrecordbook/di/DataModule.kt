package com.yveschiong.personalrecordbook.di

import com.yveschiong.data.DataRepository
import com.yveschiong.data.local.LocalDataSource
import com.yveschiong.data.local.PeopleDao
import com.yveschiong.data.local.PeopleDetailsDao
import com.yveschiong.data.mappers.PersonDataEntityMapper
import com.yveschiong.data.mappers.PersonDetailDataEntityMapper
import com.yveschiong.data.mappers.PersonDetailEntityDataMapper
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
        peopleDao: PeopleDao,
        peopleDetailsDao: PeopleDetailsDao,
        personMapper: PersonEntityDataMapper,
        personDetailMapper: PersonDetailEntityDataMapper
    ): LocalDataSource {
        return LocalDataSource(peopleDao, peopleDetailsDao, personMapper, personDetailMapper)
    }

    @Singleton
    @Provides
    fun provideAppRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        personMapper: PersonDataEntityMapper,
        personDetailMapper: PersonDetailDataEntityMapper
    ): AppRepository {
        return DataRepository(localDataSource, remoteDataSource, personMapper, personDetailMapper)
    }
}