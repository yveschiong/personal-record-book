package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.content.Context
import com.yveschiong.data.storage.InternalStorageManager
import dagger.Module
import dagger.Provides

@Module
class AddPersonDetailActivityModule {
    @Provides
    fun provideInternalStorageManager(context: Context): InternalStorageManager {
        return InternalStorageManager(context)
    }
}
