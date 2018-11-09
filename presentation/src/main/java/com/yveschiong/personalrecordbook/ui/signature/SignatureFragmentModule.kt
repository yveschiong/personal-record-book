package com.yveschiong.personalrecordbook.ui.signature

import android.content.Context
import com.yveschiong.data.storage.InternalStorageManager
import dagger.Module
import dagger.Provides

@Module
class SignatureFragmentModule {
    @Provides
    fun provideInternalStorageManager(context: Context): InternalStorageManager {
        return InternalStorageManager(context)
    }

    @Provides
    fun provideSignatureViewModelFactory(): SignatureViewModelFactory {
        return SignatureViewModelFactory()
    }
}
