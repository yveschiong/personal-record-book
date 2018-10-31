package com.yveschiong.personalrecordbook.di

import android.content.Context
import com.yveschiong.domain.AppDatabase
import com.yveschiong.domain.daos.PeopleDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.build(context)
    }

    @Provides
    @Singleton
    fun providePeopleDao(appDatabase: AppDatabase): PeopleDao {
        return appDatabase.peopleDao()
    }
}