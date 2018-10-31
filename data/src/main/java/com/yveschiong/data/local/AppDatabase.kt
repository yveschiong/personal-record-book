package com.yveschiong.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.yveschiong.domain.entities.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
}