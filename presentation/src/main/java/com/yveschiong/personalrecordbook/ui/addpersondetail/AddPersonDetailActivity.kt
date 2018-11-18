package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.os.Bundle
import android.support.v4.app.Fragment
import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseContainerActivity
import com.yveschiong.personalrecordbook.common.extensions.replaceFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AddPersonDetailActivity : BaseContainerActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var internalStorageManager: InternalStorageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person_detail)

        if (savedInstanceState == null) {
            val frag = AddPersonDetailFragment.newInstance()

            val bundle = Bundle()
            bundle.putParcelable(Constants.EXTRA_PERSON, intent?.extras?.getParcelable(Constants.EXTRA_PERSON))
            bundle.putString(Constants.EXTRA_SIGNATURE_FILE_NAME, internalStorageManager.getUniqueFilename())
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
