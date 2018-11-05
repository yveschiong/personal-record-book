package com.yveschiong.personalrecordbook.entities

import android.os.Parcelable
import com.yveschiong.personalrecordbook.common.extensions.getShortDate
import com.yveschiong.personalrecordbook.common.extensions.getTime
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetail(
    var timestamp: Long = 0,
    var duration: Float = 0.0f,
    var signature: String = "",
    var personId: Int = 0,
    var id: Int = 0
) : Parcelable {
    fun getDate(): String {
        return timestamp.getShortDate()
    }

    fun getTime(): String {
        return timestamp.getTime()
    }
}