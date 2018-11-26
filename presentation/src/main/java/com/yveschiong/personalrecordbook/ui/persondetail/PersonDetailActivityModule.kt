package com.yveschiong.personalrecordbook.ui.persondetail

import android.content.Context
import com.yveschiong.data.storage.InternalStorageManager
import dagger.Module
import dagger.Provides

@Module
class PersonDetailActivityModule {
    @Provides
    fun provideInternalStorageManager(context: Context): InternalStorageManager {
        return InternalStorageManager(context)
    }
}
