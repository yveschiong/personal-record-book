package com.yveschiong.personalrecordbook.ui.people

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.common.listeners.OnAdapterViewClicked
import com.yveschiong.personalrecordbook.common.utils.view.Refreshable
import com.yveschiong.personalrecordbook.databinding.FragmentPeopleBinding
import com.yveschiong.personalrecordbook.entities.Person
import com.yveschiong.personalrecordbook.ui.persondetail.PersonDetailActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PeopleFragment : BaseFragment<FragmentPeopleBinding>(), Refreshable {

    @Inject
    lateinit var viewModelFactory: PeopleViewModelFactory

    lateinit var viewModel: PeopleViewModel

    lateinit var adapter: PeopleAdapter

    companion object {

        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_people

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PeopleViewModel::class.java)
        binding.vm = viewModel

        viewModel.result.simpleSubscribe{ adapter.setData(it) }

        if (savedInstanceState == null) {
            // We want to make a fetch the very first time the activity has been created
            refresh()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PeopleAdapter(object : OnAdapterViewClicked<Person> {
            override fun onClicked(data: Person) {
                showPeopleDetailActivity(data)
            }
        })

        binding.recyclerView.setEmptyView(binding.emptyView)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun showPeopleDetailActivity(person: Person) {
        val intent = Intent(context, PersonDetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_PERSON, person)
        startActivity(intent)
    }

    override fun refresh() {
        viewModel.fetch()
    }
}
