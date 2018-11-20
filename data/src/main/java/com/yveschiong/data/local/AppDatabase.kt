package com.yveschiong.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.yveschiong.domain.entities.PersonDetailEntity
import com.yveschiong.domain.entities.PersonEntity

@Database(entities = [PersonEntity::class, PersonDetailEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
    abstract fun peopleDetailsDao(): PeopleDetailsDao
}