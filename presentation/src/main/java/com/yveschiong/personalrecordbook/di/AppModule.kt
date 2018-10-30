package com.yveschiong.personalrecordbook.di

import android.content.Context
import com.yveschiong.personalrecordbook.App
import dagger.Module
import dagger.Provides

/**
 * Inject application-wide dependencies.
 */
@Module
class AppModule {
    @Provides
    internal fun provideContext(application: App): Context {
        return application.applicationContext
    }
}