package com.yveschiong.data.entities

data class PersonDetailData(
    var timestamp: Long,
    var duration: Float,
    var signatureFilePath: String,
    var personId: Int,
    var id: Int = 0
)