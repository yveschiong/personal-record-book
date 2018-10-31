package com.yveschiong.domain

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.yveschiong.domain.common.Constants
import com.yveschiong.domain.daos.PeopleDao
import com.yveschiong.domain.entities.PersonEntity

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao

    companion object {
        fun build(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }
}