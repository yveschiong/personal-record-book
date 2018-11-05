package com.yveschiong.personalrecordbook.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    var firstName: String = "",
    var middleName: String = "",
    var lastName: String = "",
    var license: String = "",
    var id: Int = 0
) : Parcelable