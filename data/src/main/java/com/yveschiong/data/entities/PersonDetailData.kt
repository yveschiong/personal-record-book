package com.yveschiong.data.entities

data class PersonDetailData(
    var startTimestamp: Long,
    var endTimestamp: Long,
    var signatureFilePath: String,
    var personId: Int,
    var id: Int = 0
)