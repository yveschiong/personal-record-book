package com.yveschiong.data.datastores

import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface Cache {
    fun getPeople(): Single<List<PersonEntity>>
}
