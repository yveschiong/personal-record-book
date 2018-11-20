package com.yveschiong.personalrecordbook.entities

import android.os.Parcelable
import com.yveschiong.personalrecordbook.common.extensions.getShortDate
import com.yveschiong.personalrecordbook.common.extensions.getTime
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetail(
    var startTimestamp: Long = 0,
    var endTimestamp: Long = 0,
    var signatureFilePath: String = "",
    var personId: Int = 0,
    var id: Int = 0
) : Parcelable {
    fun getStartDate(): String {
        return startTimestamp.getShortDate()
    }

    fun getStartTime(): String {
        return startTimestamp.getTime()
    }

    fun getEndDate(): String {
        return endTimestamp.getShortDate()
    }

    fun getEndTime(): String {
        return endTimestamp.getTime()
    }
}