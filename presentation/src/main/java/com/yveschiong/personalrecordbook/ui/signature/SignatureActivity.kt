package com.yveschiong.personalrecordbook.ui.signature

import android.os.Bundle
import android.support.v4.app.Fragment
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.base.BaseActivity
import com.yveschiong.personalrecordbook.common.extensions.replaceFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SignatureActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature)
        if (savedInstanceState == null) {
            replaceFragment(R.id.container, SignatureFragment.newInstance())
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
