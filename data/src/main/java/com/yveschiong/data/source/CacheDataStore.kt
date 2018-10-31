package com.yveschiong.data.source

import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface CacheDataStore {
    fun getPeople(): Single<List<PersonEntity>>
}
