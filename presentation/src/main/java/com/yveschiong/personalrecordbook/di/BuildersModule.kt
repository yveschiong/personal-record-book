package com.yveschiong.personalrecordbook.di

import com.yveschiong.personalrecordbook.MainActivity
import com.yveschiong.personalrecordbook.ui.addperson.AddPersonActivity
import com.yveschiong.personalrecordbook.ui.addperson.AddPersonFragment
import com.yveschiong.personalrecordbook.ui.addperson.AddPersonFragmentModule
import com.yveschiong.personalrecordbook.ui.people.PeopleFragment
import com.yveschiong.personalrecordbook.ui.people.PeopleFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {
    // Activities
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindAddPersonActivity(): AddPersonActivity

    // Fragments
    @ContributesAndroidInjector(modules = [PeopleFragmentModule::class])
    abstract fun bindPeopleFragment(): PeopleFragment

    @ContributesAndroidInjector(modules = [AddPersonFragmentModule::class])
    abstract fun bindAddPersonFragment(): AddPersonFragment
}