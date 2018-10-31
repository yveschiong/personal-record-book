package com.yveschiong.data.datastores

import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface DataStore {
    fun getPeople(): Single<List<PersonEntity>>
}
