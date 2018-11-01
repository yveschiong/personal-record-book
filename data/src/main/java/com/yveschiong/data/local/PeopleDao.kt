package com.yveschiong.data.local

import android.arch.persistence.room.*
import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

@Dao
interface PeopleDao {
    @Query("SELECT * FROM people")
    fun getPeople(): Single<List<PersonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: PersonEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(person: PersonEntity)

    @Delete
    fun delete(person: PersonEntity)

    @Query("DELETE FROM people")
    fun deleteAll()
}