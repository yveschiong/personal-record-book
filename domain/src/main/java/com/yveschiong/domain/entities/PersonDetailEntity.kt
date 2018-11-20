package com.yveschiong.domain.entities

import android.arch.persistence.room.*
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "people_details",
    indices = [Index(value = ["person_id"], name = "index_people_details_person_id")],
    foreignKeys = [ForeignKey(entity = PersonEntity::class,
        parentColumns = ["id"],
        childColumns = ["person_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)]
)
data class PersonDetailEntity(
    @ColumnInfo(name = "start_timestamp")
    var startTimestamp: Long,

    @ColumnInfo(name = "end_timestamp")
    var endTimestamp: Long,

    @ColumnInfo(name = "signature_file_path")
    var signatureFilePath: String,

    @ColumnInfo(name = "person_id")
    var personId: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) : Parcelable