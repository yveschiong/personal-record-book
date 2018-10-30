package com.yveschiong.personalrecordbook.di;

import com.yveschiong.personalrecordbook.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}