package com.yveschiong.personalrecordbook.di

import com.yveschiong.personalrecordbook.MainActivity
import com.yveschiong.personalrecordbook.ui.addperson.AddPersonActivity
import com.yveschiong.personalrecordbook.ui.addperson.AddPersonFragment
import com.yveschiong.personalrecordbook.ui.addperson.AddPersonFragmentModule
import com.yveschiong.personalrecordbook.ui.addpersondetail.AddPersonDetailActivity
import com.yveschiong.personalrecordbook.ui.addpersondetail.AddPersonDetailFragment
import com.yveschiong.personalrecordbook.ui.addpersondetail.AddPersonDetailFragmentModule
import com.yveschiong.personalrecordbook.ui.people.PeopleFragment
import com.yveschiong.personalrecordbook.ui.people.PeopleFragmentModule
import com.yveschiong.personalrecordbook.ui.persondetail.PersonDetailActivity
import com.yveschiong.personalrecordbook.ui.persondetail.PersonDetailFragment
import com.yveschiong.personalrecordbook.ui.persondetail.PersonDetailFragmentModule

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

    @ContributesAndroidInjector
    abstract fun bindPersonDetailActivity(): PersonDetailActivity

    @ContributesAndroidInjector
    abstract fun bindAddPersonDetailActivity(): AddPersonDetailActivity

    // Fragments
    @ContributesAndroidInjector(modules = [PeopleFragmentModule::class])
    abstract fun bindPeopleFragment(): PeopleFragment

    @ContributesAndroidInjector(modules = [AddPersonFragmentModule::class])
    abstract fun bindAddPersonFragment(): AddPersonFragment

    @ContributesAndroidInjector(modules = [PersonDetailFragmentModule::class])
    abstract fun bindPersonDetailFragment(): PersonDetailFragment

    @ContributesAndroidInjector(modules = [AddPersonDetailFragmentModule::class])
    abstract fun bindAddPersonDetailFragment(): AddPersonDetailFragment
}