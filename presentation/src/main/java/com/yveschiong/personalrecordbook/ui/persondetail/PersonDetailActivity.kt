package com.yveschiong.personalrecordbook.ui.persondetail

import android.os.Bundle
import android.support.v4.app.Fragment
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseContainerActivity
import com.yveschiong.personalrecordbook.common.extensions.replaceFragment
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PersonDetailActivity : BaseContainerActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)

        val person: Person? = intent?.extras?.getParcelable(Constants.EXTRA_PERSON)
        person?.let {
            title = if (it.middleName.isEmpty()) {
                getString(R.string.full_name, person.firstName, person.lastName)
            } else {
                getString(R.string.extended_name, person.firstName, person.middleName, person.lastName)
            }
        }

        if (savedInstanceState == null) {
            val frag = PersonDetailFragment.newInstance()

            val bundle = Bundle()
            bundle.putParcelable(Constants.EXTRA_PERSON, person)
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
