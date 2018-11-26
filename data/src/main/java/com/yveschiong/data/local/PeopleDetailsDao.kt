package com.yveschiong.data.local

import android.arch.persistence.room.*
import com.yveschiong.domain.entities.PersonDetailEntity
import io.reactivex.Single

@Dao
interface PeopleDetailsDao {
    @Query("SELECT * FROM people_details")
    fun getPeopleDetails(): Single<List<PersonDetailEntity>>

    @Query("SELECT * FROM people_details WHERE person_id = :personId ORDER BY start_timestamp ASC")
    fun getPersonDetails(personId: Int): Single<List<PersonDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(detail: PersonDetailEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(detail: PersonDetailEntity)

    @Delete
    fun delete(detail: PersonDetailEntity): Int

    @Query("DELETE FROM people_details")
    fun deleteAll()
}