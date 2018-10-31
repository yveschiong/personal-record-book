package com.yveschiong.domain.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "people",
    indices = [Index(value = ["id"], name = "index_people_id", unique = true)]
)
data class PersonEntity(
    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "middleName")
    var middleName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) : Parcelable