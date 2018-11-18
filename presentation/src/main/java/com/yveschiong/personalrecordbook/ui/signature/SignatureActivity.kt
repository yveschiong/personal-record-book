package com.yveschiong.personalrecordbook.ui.signature

import android.os.Bundle
import android.support.v4.app.Fragment
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseContainerActivity
import com.yveschiong.personalrecordbook.common.extensions.replaceFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class SignatureActivity : BaseContainerActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature)

        if (savedInstanceState == null) {
            val frag = SignatureFragment.newInstance()

            val bundle = Bundle()
            bundle.putParcelable(Constants.EXTRA_PERSON, intent?.extras?.getParcelable(Constants.EXTRA_PERSON))
            bundle.putString(Constants.EXTRA_SIGNATURE_FILE_NAME, intent?.extras?.getString(Constants.EXTRA_SIGNATURE_FILE_NAME))
            frag.arguments = bundle

            replaceFragment(getContainerId(), frag)
        }
    }

    override fun getContainerId(): Int {
        return R.id.container
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
