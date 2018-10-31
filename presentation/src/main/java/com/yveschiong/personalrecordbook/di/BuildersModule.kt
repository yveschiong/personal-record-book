package com.yveschiong.personalrecordbook.di

import com.yveschiong.personalrecordbook.MainActivity
import com.yveschiong.personalrecordbook.ui.people.PeopleFragment
import com.yveschiong.personalrecordbook.ui.people.PeopleFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [PeopleFragmentModule::class])
    abstract fun bindPeopleFragment(): PeopleFragment
}