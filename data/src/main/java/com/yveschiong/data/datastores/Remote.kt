package com.yveschiong.data.datastores

import com.yveschiong.domain.entities.PersonEntity
import io.reactivex.Single

interface Remote {
    fun getPeople(): Single<List<PersonEntity>>
}
