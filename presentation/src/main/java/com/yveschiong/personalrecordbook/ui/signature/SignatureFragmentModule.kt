package com.yveschiong.personalrecordbook.ui.signature

import dagger.Module
import dagger.Provides

@Module
class SignatureFragmentModule {
    @Provides
    fun provideSignatureViewModelFactory(): SignatureViewModelFactory {
        return SignatureViewModelFactory()
    }
}
