package com.yveschiong.personalrecordbook.common.metadata

data class ImageMetadata (var timestamp: Long = 0)

fun ImageMetadata?.isNullOrEmpty(): Boolean {
    return this == null || timestamp == 0L
}