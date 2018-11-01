package com.yveschiong.data.entities

data class PersonData(
    var firstName: String,
    var middleName: String,
    var lastName: String,
    var license: String,
    var id: Int = 0
)