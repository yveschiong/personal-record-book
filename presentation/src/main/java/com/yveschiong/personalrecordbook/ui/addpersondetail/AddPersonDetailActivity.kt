package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.os.Bundle
import android.support.v4.app.Fragment
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseActivity
import com.yveschiong.personalrecordbook.common.extensions.replaceFragment
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AddPersonDetailActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person_detail)

        val person: Person? = intent?.extras?.getParcelable(Constants.EXTRA_PERSON)

        if (savedInstanceState == null) {
            val frag = AddPersonDetailFragment.newInstance()

            val bundle = Bundle()
            bundle.putParcelable(Constants.EXTRA_PERSON, person)
            frag.arguments = bundle

            replaceFragment(R.id.container, frag)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}
