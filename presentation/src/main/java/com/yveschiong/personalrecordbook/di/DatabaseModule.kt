package com.yveschiong.personalrecordbook.di

import android.arch.persistence.room.Room
import android.content.Context
import com.yveschiong.data.local.AppDatabase
import com.yveschiong.data.local.PeopleDao
import com.yveschiong.personalrecordbook.common.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providePeopleDao(appDatabase: AppDatabase): PeopleDao {
        return appDatabase.peopleDao()
    }
}